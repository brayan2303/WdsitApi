/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.controller;

import net.woden.wdsit.entity.ProPerspectiveEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.ProPerspectiveService;
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
@RequestMapping("proPerspective/")
public class ProPerspectiveController {
    
    @Autowired
    private ProPerspectiveService proPerspectiveS; 

    @PostMapping(value="create")
    public ResponseEntity create(@RequestBody ProPerspectiveEntity b){
        ResponseModel response=this.proPerspectiveS.create(b); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="update")
    public ResponseEntity update(@RequestBody ProPerspectiveEntity b){
        ResponseModel response=this.proPerspectiveS.update(b); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{perspectiveId}")
    public ResponseEntity delete(@PathVariable int perspectiveId){
        ResponseModel response=this.proPerspectiveS.delete(perspectiveId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list/{year}/{countryId}")
    public ResponseEntity list(@PathVariable int year,@PathVariable int countryId){
        ResponseModel response=this.proPerspectiveS.list(year,countryId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="listActive/{year}/{personId}/{countryId}")
    public ResponseEntity listActive(@PathVariable int year,@PathVariable int personId,@PathVariable int countryId){
        ResponseModel response=this.proPerspectiveS.listActive(year,personId,countryId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="total/{year}/{countryId}")
    public ResponseEntity total(@PathVariable int year,@PathVariable int countryId){
        ResponseModel response=this.proPerspectiveS.total(year,countryId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="percentage/{year}")
    public ResponseEntity percentage(@PathVariable int year){
        ResponseModel response=this.proPerspectiveS.percentage(year); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="percentageMonth/{perspectiveId}")
    public ResponseEntity percentageMonth(@PathVariable int perspectiveId){
        ResponseModel response=this.proPerspectiveS.percentageMonth(perspectiveId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
