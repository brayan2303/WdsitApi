package net.woden.wdsit.controller;

import net.woden.wdsit.entity.FilSerialEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.FilSerialService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("filSerial/")
public class FilSerialController {
 
    @Autowired
    private FilSerialService filSerialS; 

    @PostMapping(value="create")
    public ResponseEntity create(@RequestBody FilSerialEntity f){
        ResponseModel response=this.filSerialS.create(f); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="update")
    public ResponseEntity update(@RequestBody FilSerialEntity f){
        ResponseModel response=this.filSerialS.update(f); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{serialId}")
    public ResponseEntity delete(@PathVariable int serialId){
        ResponseModel response=this.filSerialS.delete(serialId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PostMapping(value="loadFile/{serialId}/{customer}/{type}")
    public ResponseEntity loadFile(@PathVariable String serialId,@PathVariable String customer,@PathVariable String type,@RequestParam("files") MultipartFile[] files){
        ResponseModel response=this.filSerialS.loadFile(serialId,customer,type,files); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list/{countryId}/{customerId}")
    public ResponseEntity list(@PathVariable int countryId,@PathVariable int customerId){
        ResponseModel response=this.filSerialS.list(countryId,customerId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="listFile/{serial}/{customer}/{type}")
    public ResponseEntity listFile(@PathVariable String serial,@PathVariable String customer,@PathVariable String type){
        ResponseModel response=this.filSerialS.listFile(serial,customer,type); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="deleteFile/{serialId}/{customer}/{type}/{fileName}")
    public ResponseEntity deleteFile(@PathVariable String serialId,@PathVariable String customer,@PathVariable String type,@PathVariable String fileName){
        ResponseModel response=this.filSerialS.deleteFile(serialId,customer,type,fileName); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="serialStatus/{serial}")
    public ResponseEntity serialStatus(@PathVariable String serial){
        ResponseModel response=this.filSerialS.serialStatus(serial); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="deleteFileBySerial/{customer}/{serialId}")
    public ResponseEntity deleteFileBySerial(@PathVariable String customer,@PathVariable String serialId){
        ResponseModel response=this.filSerialS.deleteFileBySerial(customer,serialId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @GetMapping(value="serialSearch/{serial}")
    public ResponseEntity serialSearch(@PathVariable String serial){
        ResponseModel response=this.filSerialS.serialSearch(serial); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
