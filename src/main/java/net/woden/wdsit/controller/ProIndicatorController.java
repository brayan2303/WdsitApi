/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.controller;

import net.woden.wdsit.entity.ProIndicatorEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.ProIndicatorService;
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
@RequestMapping("proIndicator/")
public class ProIndicatorController {
    
    @Autowired
    private ProIndicatorService proIndicatorS; 

    @PostMapping(value="create")
    public ResponseEntity create(@RequestBody ProIndicatorEntity b){
        ResponseModel response=this.proIndicatorS.create(b); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="update")
    public ResponseEntity update(@RequestBody ProIndicatorEntity b){
        ResponseModel response=this.proIndicatorS.update(b); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{indicatorId}")
    public ResponseEntity delete(@PathVariable int indicatorId){
        ResponseModel response=this.proIndicatorS.delete(indicatorId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list/{strategicObjetiveId}")
    public ResponseEntity list(@PathVariable int strategicObjetiveId){
        ResponseModel response=this.proIndicatorS.list(strategicObjetiveId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="listActive/{strategicObjetiveId}")
    public ResponseEntity listActive(@PathVariable int strategicObjetiveId){
        ResponseModel response=this.proIndicatorS.listActive(strategicObjetiveId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="percentage/{strategicObjetiveId}")
    public ResponseEntity percentage(@PathVariable int strategicObjetiveId){
        ResponseModel response=this.proIndicatorS.percentage(strategicObjetiveId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="percentageMonth/{indicatorId}")
    public ResponseEntity percentageMonth(@PathVariable int indicatorId){
        ResponseModel response=this.proIndicatorS.percentageMonth(indicatorId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="total/{strategicObjetiveId}")
    public ResponseEntity total(@PathVariable int strategicObjetiveId){
        ResponseModel response=this.proIndicatorS.total(strategicObjetiveId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
