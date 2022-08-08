package net.woden.wdsit.controller;

import java.util.ArrayList;
import net.woden.wdsit.entity.InvSerialEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.InvSerialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("invSerial/")
public class InvSerialController {
 
    @Autowired
    private InvSerialService invSerialS; 

    @PostMapping(value="create/{coutingType}")
    public ResponseEntity create(@PathVariable String coutingType,@RequestBody InvSerialEntity i){
        ResponseModel response=this.invSerialS.create(coutingType,i); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PostMapping(value="createAll/{coutingType}/{coutingId}/{creationUserId}")
    public ResponseEntity createAll(@PathVariable String coutingType,@PathVariable int coutingId,@PathVariable int creationUserId,@RequestBody ArrayList<InvSerialEntity>array){
        ResponseModel response=this.invSerialS.createAll(coutingType,coutingId,creationUserId,array);
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{coutingType}/{serialId}")
    public ResponseEntity delete(@PathVariable String coutingType,@PathVariable int serialId){
        ResponseModel response=this.invSerialS.delete(coutingType,serialId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list/{coutingId}/{coutingType}")
    public ResponseEntity list(@PathVariable int coutingId,@PathVariable String coutingType){
        ResponseModel response=this.invSerialS.list(coutingId,coutingType); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findSap/{serial}/{customer}")
    public ResponseEntity findSap(@PathVariable String serial,@PathVariable String customer){
        ResponseModel response=this.invSerialS.findSap(serial,customer); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findWms/{serial}/{customer}")
    public ResponseEntity findWms(@PathVariable String serial,@PathVariable String customer){
        ResponseModel response=this.invSerialS.findWms(serial,customer); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="find/{coutingId}/{coutingType}/{serial}")
    public ResponseEntity find(@PathVariable int coutingId,@PathVariable String coutingType,@PathVariable String serial){
        ResponseModel response=this.invSerialS.find(coutingId,coutingType,serial); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
