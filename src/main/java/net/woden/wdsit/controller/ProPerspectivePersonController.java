/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.controller;

import net.woden.wdsit.entity.ProPerspectivePersonEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.ProPerspectivePersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author b.algecira
 */
@RestController
@RequestMapping("proPerspectivePerson/")
public class ProPerspectivePersonController {
    
    @Autowired
    private ProPerspectivePersonService proPerspectivePersonS; 

    @PostMapping(value="create")
    public ResponseEntity create(@RequestBody ProPerspectivePersonEntity b){
        ResponseModel response=this.proPerspectivePersonS.create(b); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{perspectiveId}/{personId}")
    public ResponseEntity delete(@PathVariable int perspectiveId,@PathVariable int personId){
        ResponseModel response=this.proPerspectivePersonS.delete(perspectiveId,personId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list/{perspectiveId}")
    public ResponseEntity list(@PathVariable int perspectiveId){
        ResponseModel response=this.proPerspectivePersonS.list(perspectiveId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="listActive/{year}/{personId}/{countryId}")
    public ResponseEntity listActive(@PathVariable int year,@PathVariable int personId,@PathVariable int countryId){
        ResponseModel response=this.proPerspectivePersonS.listActive(year,personId,countryId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
