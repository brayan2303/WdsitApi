package net.woden.wdsit.controller;

import net.woden.wdsit.entity.InvPalletEntity;
import net.woden.wdsit.model.InvCyclicPalletModel;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.InvPalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("invPallet/")
public class InvPalletController {
 
    @Autowired
    private InvPalletService invPalletS; 

    @PostMapping(value="create")
    public ResponseEntity create(@RequestBody InvPalletEntity i){
        ResponseModel response=this.invPalletS.create(i); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{cyclicId}/{number}")
    public ResponseEntity delete(@PathVariable int cyclicId,@PathVariable String number){
        ResponseModel response=this.invPalletS.delete(cyclicId,number); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="approveReject/{palletId}/{status}")
    public ResponseEntity approveReject(@PathVariable int palletId,@PathVariable String status){
        ResponseModel response=this.invPalletS.approveReject(palletId,status); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="find/{cyclicId}/{number}")
    public ResponseEntity find(@PathVariable int cyclicId,@PathVariable String number){
        ResponseModel response=this.invPalletS.find(cyclicId,number); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findAll/{cyclicId}/{system}/{typeSampling}/{type}/{customer}")
    public ResponseEntity findAll(@PathVariable int cyclicId,@PathVariable String system,@PathVariable String typeSampling,@PathVariable String type,@PathVariable String customer){
        ResponseModel response=this.invPalletS.findAll(cyclicId,system,typeSampling,type,customer); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findPending/{cyclicId}")
    public ResponseEntity findPending(@PathVariable int cyclicId){
        ResponseModel response=this.invPalletS.findPending(cyclicId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findSerials/{cyclicId}/{system}/{type}/{number}/{customer}/{option}")
    public ResponseEntity findSerials(@PathVariable int cyclicId,@PathVariable String system,@PathVariable String type,@PathVariable String number,@PathVariable String customer,@PathVariable int option){
        ResponseModel response=this.invPalletS.findSerials(cyclicId,system,type,number,customer,option); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findCouting/{palletId}")
    public ResponseEntity findCouting(@PathVariable int palletId){
        ResponseModel response=this.invPalletS.findCouting(palletId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list/{cyclicId}/{system}/{type}/{customer}")
    public ResponseEntity list(@PathVariable int cyclicId,@PathVariable String system,@PathVariable String type,@PathVariable String customer){
        ResponseModel response=this.invPalletS.list(cyclicId,system,type,customer); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="listAll/{cyclicId}/{system}/{customer}")
    public ResponseEntity listAll(@PathVariable int cyclicId,@PathVariable String system,@PathVariable String customer){
        ResponseModel response=this.invPalletS.listAll(cyclicId,system,customer); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PostMapping(value="print/{customer}/{status}")
    public ResponseEntity print(@PathVariable String customer,@PathVariable String status,@RequestBody InvCyclicPalletModel i){
        ResponseModel response=this.invPalletS.print(customer,status,i); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
