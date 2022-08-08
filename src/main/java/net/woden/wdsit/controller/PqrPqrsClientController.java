/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.controller;


import net.woden.wdsit.entity.PqrPqrsClientEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.PqrPqrsClientService;
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

/**
 *
 * @author b.algecira
 */
@RestController
@RequestMapping("PqrPqrsClientS/")
public class PqrPqrsClientController {
    
    
    @Autowired
    private PqrPqrsClientService pqrPqrsClientS; 

    @PostMapping(value="create")
    public ResponseEntity create(@RequestBody PqrPqrsClientEntity p){
        ResponseModel response=this.pqrPqrsClientS.create(p); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="update")
    public ResponseEntity update(@RequestBody PqrPqrsClientEntity p){
        ResponseModel response=this.pqrPqrsClientS.update(p); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="eventUpdate")
    public ResponseEntity eventUpdate(@RequestBody PqrPqrsClientEntity p){
        ResponseModel response=this.pqrPqrsClientS.eventUpdate(p); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{pqrsId}")
    public ResponseEntity delete(@PathVariable int pqrsId){
        ResponseModel response=this.pqrPqrsClientS.delete(pqrsId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="manage/{pqrsId}/{assignedPersonId}/{statusId}/{type}")
    public ResponseEntity manage(@PathVariable int pqrsId,@PathVariable int assignedPersonId,@PathVariable int statusId,@PathVariable String type){
        ResponseModel response=this.pqrPqrsClientS.manage(pqrsId,assignedPersonId,statusId,type); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="finish/{pqrsId}/{managementStatusId}/{responseDateCustomerPqrs}/{finalContactMethodId}/{statusId}/{observations}")
    public ResponseEntity finish(@PathVariable int pqrsId,@PathVariable int managementStatusId,@PathVariable String responseDateCustomerPqrs,@PathVariable int finalContactMethodId,@PathVariable int statusId,@PathVariable String observations){
        ResponseModel response=this.pqrPqrsClientS.finish(pqrsId,managementStatusId,responseDateCustomerPqrs,finalContactMethodId,statusId,observations); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="find/{identificationNumber}/{ticket}/{numero}/{serialImei}")
    public ResponseEntity find(@PathVariable String identificationNumber,@PathVariable String ticket,@PathVariable String numero,@PathVariable String serialImei){
        ResponseModel response=this.pqrPqrsClientS.find(identificationNumber,ticket,numero,serialImei); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findById/{pqrsId}")
    public ResponseEntity findById(@PathVariable int pqrsId){
        ResponseModel response=this.pqrPqrsClientS.findById(pqrsId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findByNumber/{number}")
    public ResponseEntity findByNumber(@PathVariable String number){
        ResponseModel response=this.pqrPqrsClientS.findByNumber(number); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list/{personId}/{status}/{initialDate}/{finalDate}")
    public ResponseEntity list(@PathVariable int personId,@PathVariable String status,@PathVariable String initialDate,@PathVariable String finalDate){
        ResponseModel response=this.pqrPqrsClientS.list(personId,status,initialDate,finalDate); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PostMapping(value="loadFile/{pqrsNumber}/{type}")
    public ResponseEntity loadFile(@PathVariable String pqrsNumber,@PathVariable String type,@RequestParam("files") MultipartFile[] files){
        ResponseModel response=this.pqrPqrsClientS.loadFile(pqrsNumber,type,files); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="deleteFile/{pqrsNumber}/{type}/{fileName}")
    public ResponseEntity deleteFile(@PathVariable String pqrsNumber,@PathVariable String type,@PathVariable String fileName){
        ResponseModel response=this.pqrPqrsClientS.deleteFile(pqrsNumber,type,fileName); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="deleteFileByPqrs/{pqrsNumber}")
    public ResponseEntity deleteFileByPqrs(@PathVariable String pqrsNumber){
        ResponseModel response=this.pqrPqrsClientS.deleteFileByPqrs(pqrsNumber); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
