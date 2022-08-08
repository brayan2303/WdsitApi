/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.controller;

import net.woden.wdsit.entity.ProActivityEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.ProActivityService;
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
@RequestMapping("proActivity/")
public class ProActivityController {
    
    @Autowired
    private ProActivityService proActivityS; 

    @PostMapping(value="create")
    public ResponseEntity create(@RequestBody ProActivityEntity b){
        ResponseModel response=this.proActivityS.create(b); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="update")
    public ResponseEntity update(@RequestBody ProActivityEntity b){
        ResponseModel response=this.proActivityS.update(b); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{activityId}")
    public ResponseEntity delete(@PathVariable int activityId){
        ResponseModel response=this.proActivityS.delete(activityId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="openClose/{activityId}/{status}")
    public ResponseEntity openClose(@PathVariable int activityId,@PathVariable String status){
        ResponseModel response=this.proActivityS.openClose(activityId,status); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list/{workPlanId}")
    public ResponseEntity list(@PathVariable int workPlanId){
        ResponseModel response=this.proActivityS.list(workPlanId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
