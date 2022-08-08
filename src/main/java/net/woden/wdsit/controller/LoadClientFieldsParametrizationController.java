/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.woden.wdsit.controller;

import net.woden.wdsit.entity.LoadClientFieldsParametrizationEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.LoadClientFieldsParametrizationService;
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
@RequestMapping("LoadClientFieldsParametrizationS/")
public class LoadClientFieldsParametrizationController {
    
    @Autowired
    private LoadClientFieldsParametrizationService LoadClientFieldsParametrizationS;
    
    @PostMapping(value = "create")
    public ResponseEntity create(@RequestBody LoadClientFieldsParametrizationEntity l){
    ResponseModel response = this.LoadClientFieldsParametrizationS.create(l);
    return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
     @GetMapping(value = "list/{fieldId}")
    public ResponseEntity list (@PathVariable int fieldId){
    ResponseModel response = this.LoadClientFieldsParametrizationS.list(fieldId);
    return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @DeleteMapping(value = "delete/{id}") //servicio eliminar
    public ResponseEntity delete(@PathVariable int id) {
        ResponseModel response = this.LoadClientFieldsParametrizationS.delete(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
