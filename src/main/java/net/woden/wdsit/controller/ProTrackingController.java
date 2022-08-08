/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.controller;

import net.woden.wdsit.entity.ProTrackingEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.ProTrackingService;
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
@RequestMapping("proTracking/")
public class ProTrackingController {
    
    @Autowired
    private ProTrackingService proTrackingS; 

    @PostMapping(value="create")
    public ResponseEntity create(@RequestBody ProTrackingEntity b){
        ResponseModel response=this.proTrackingS.create(b); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="update")
    public ResponseEntity update(@RequestBody ProTrackingEntity b){
        ResponseModel response=this.proTrackingS.update(b); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="approveReject/{trackingId}/{status}")
    public ResponseEntity approveReject(@PathVariable int trackingId,@PathVariable String status){
        ResponseModel response=this.proTrackingS.approveReject(trackingId,status); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{trackingId}")
    public ResponseEntity delete(@PathVariable int trackingId){
        ResponseModel response=this.proTrackingS.delete(trackingId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list/{actionPlanId}")
    public ResponseEntity list(@PathVariable int actionPlanId){
        ResponseModel response=this.proTrackingS.list(actionPlanId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
