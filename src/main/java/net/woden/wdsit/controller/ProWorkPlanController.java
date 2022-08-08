/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.controller;

import net.woden.wdsit.entity.ProWorkPlanEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.ProWorkPlanService;
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
@RequestMapping("proWorkPlan/")
public class ProWorkPlanController {
    
    @Autowired
    private ProWorkPlanService proWorkPlanS; 

    @PostMapping(value="create")
    public ResponseEntity create(@RequestBody ProWorkPlanEntity b){
        ResponseModel response=this.proWorkPlanS.create(b); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="update")
    public ResponseEntity update(@RequestBody ProWorkPlanEntity b){
        ResponseModel response=this.proWorkPlanS.update(b); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{workPlanId}")
    public ResponseEntity delete(@PathVariable int workPlanId){
        ResponseModel response=this.proWorkPlanS.delete(workPlanId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list/{strategicObjetiveId}")
    public ResponseEntity list(@PathVariable int strategicObjetiveId){
        ResponseModel response=this.proWorkPlanS.list(strategicObjetiveId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="listActive/{strategicObjetiveId}")
    public ResponseEntity listActive(@PathVariable int strategicObjetiveId){
        ResponseModel response=this.proWorkPlanS.listActive(strategicObjetiveId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="total/{strategicObjetiveId}")
    public ResponseEntity total(@PathVariable int strategicObjetiveId){
        ResponseModel response=this.proWorkPlanS.total(strategicObjetiveId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="percentage/{strategicObjetiveId}")
    public ResponseEntity percentage(@PathVariable int strategicObjetiveId){
        ResponseModel response=this.proWorkPlanS.percentage(strategicObjetiveId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="percentageAdvances/{workPlanId}")
    public ResponseEntity percentageAdvances(@PathVariable int workPlanId){
        ResponseModel response=this.proWorkPlanS.percentageAdvances(workPlanId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
