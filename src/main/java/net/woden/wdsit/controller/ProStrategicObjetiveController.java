/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.controller;

import net.woden.wdsit.entity.ProStrategicObjetiveEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.ProStrategicObjetiveService;
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
@RequestMapping("proStrategicObjetive/")
public class ProStrategicObjetiveController {
    
    @Autowired
    private ProStrategicObjetiveService proStrategicObjetiveS; 

    @PostMapping(value="create")
    public ResponseEntity create(@RequestBody ProStrategicObjetiveEntity b){
        ResponseModel response=this.proStrategicObjetiveS.create(b); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="update")
    public ResponseEntity update(@RequestBody ProStrategicObjetiveEntity b){
        ResponseModel response=this.proStrategicObjetiveS.update(b); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{strategicObjetiveId}")
    public ResponseEntity delete(@PathVariable int strategicObjetiveId){
        ResponseModel response=this.proStrategicObjetiveS.delete(strategicObjetiveId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list/{perspectiveId}")
    public ResponseEntity list(@PathVariable int perspectiveId){
        ResponseModel response=this.proStrategicObjetiveS.list(perspectiveId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="listActive/{perspectiveId}")
    public ResponseEntity listActive(@PathVariable int perspectiveId){
        ResponseModel response=this.proStrategicObjetiveS.listActive(perspectiveId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="percentage/{perspectiveId}")
    public ResponseEntity percentage(@PathVariable int perspectiveId){
        ResponseModel response=this.proStrategicObjetiveS.percentage(perspectiveId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="percentageMonth/{strategicObjetiveId}")
    public ResponseEntity percentageMonth(@PathVariable int strategicObjetiveId){
        ResponseModel response=this.proStrategicObjetiveS.percentageMonth(strategicObjetiveId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="total/{perspectiveId}")
    public ResponseEntity total(@PathVariable int perspectiveId){
        ResponseModel response=this.proStrategicObjetiveS.total(perspectiveId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
