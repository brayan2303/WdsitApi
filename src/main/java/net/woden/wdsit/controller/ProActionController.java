/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.controller;

import net.woden.wdsit.entity.ProActionEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.ProActionService;
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

/**
 *
 * @author b.algecira
 */
@RestController
@RequestMapping("proAction/")
public class ProActionController {
    
    @Autowired
    private ProActionService proActionS; 

    @PostMapping(value="create")
    public ResponseEntity create(@RequestBody ProActionEntity b){
        ResponseModel response=this.proActionS.create(b); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="update")
    public ResponseEntity update(@RequestBody ProActionEntity b){
        ResponseModel response=this.proActionS.update(b); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="openClose/{actionId}/{openClose}")
    public ResponseEntity openClose(@PathVariable int actionId,@PathVariable boolean openClose){
        ResponseModel response=this.proActionS.openClose(actionId,openClose); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{actionId}")
    public ResponseEntity delete(@PathVariable int actionId){
        ResponseModel response=this.proActionS.delete(actionId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findById/{actionId}")
    public ResponseEntity findById(@PathVariable int actionId){
        ResponseModel response=this.proActionS.findById(actionId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findOpen/{actionPlanId}")
    public ResponseEntity findOpen(@PathVariable int actionPlanId){
        ResponseModel response=this.proActionS.findOpen(actionPlanId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list/{actionPlanId}")
    public ResponseEntity list(@PathVariable int actionPlanId){
        ResponseModel response=this.proActionS.list(actionPlanId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
