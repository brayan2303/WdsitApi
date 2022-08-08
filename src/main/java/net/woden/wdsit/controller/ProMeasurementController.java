/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.controller;

import net.woden.wdsit.entity.ProMeasurementEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.ProMeasurementService;
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
@RequestMapping("proMeasurement/")
public class ProMeasurementController {
    
    @Autowired
    private ProMeasurementService proMeasurementS; 

    @PostMapping(value="create")
    public ResponseEntity create(@RequestBody ProMeasurementEntity b){
        ResponseModel response=this.proMeasurementS.create(b); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="update")
    public ResponseEntity update(@RequestBody ProMeasurementEntity b){
        ResponseModel response=this.proMeasurementS.update(b); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{measurementId}")
    public ResponseEntity delete(@PathVariable int measurementId){
        ResponseModel response=this.proMeasurementS.delete(measurementId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list/{indicatorId}/{personId}")
    public ResponseEntity list(@PathVariable int indicatorId,@PathVariable int personId){
        ResponseModel response=this.proMeasurementS.list(indicatorId,personId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="listActive/{indicatorId}/{personId}")
    public ResponseEntity listActive(@PathVariable int indicatorId,@PathVariable int personId){
        ResponseModel response=this.proMeasurementS.listActive(indicatorId,personId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
