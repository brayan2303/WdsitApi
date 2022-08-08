/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.controller;

import net.woden.wdsit.entity.ProActionPlanEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.ProActionPlanService;
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
@RequestMapping("proActionPlan/")
public class ProActionPlanController {
    
    @Autowired
    private ProActionPlanService proActionPlanS; 

    @PostMapping(value="create")
    public ResponseEntity create(@RequestBody ProActionPlanEntity b){
        ResponseModel response=this.proActionPlanS.create(b); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="update")
    public ResponseEntity update(@RequestBody ProActionPlanEntity b){
        ResponseModel response=this.proActionPlanS.update(b); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{actionPlanId}")
    public ResponseEntity delete(@PathVariable int actionPlanId){
        ResponseModel response=this.proActionPlanS.delete(actionPlanId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="openClose/{actionPlanId}/{openClose}")
    public ResponseEntity openClose(@PathVariable int actionPlanId,@PathVariable boolean openClose){
        ResponseModel response=this.proActionPlanS.openClose(actionPlanId,openClose); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="approveReject/{actionPlanId}/{status}")
    public ResponseEntity approveReject(@PathVariable int actionPlanId,@PathVariable String status){
        ResponseModel response=this.proActionPlanS.approveReject(actionPlanId,status); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findById/{actionPlanId}")
    public ResponseEntity findById(@PathVariable int actionPlanId){
        ResponseModel response=this.proActionPlanS.findById(actionPlanId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list/{analysisId}")
    public ResponseEntity list(@PathVariable int analysisId){
        ResponseModel response=this.proActionPlanS.list(analysisId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
