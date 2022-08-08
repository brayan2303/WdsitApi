/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.woden.wdsit.controller;

import net.woden.wdsit.entity.LoadClientStarParameterizationEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.LoadClientStarParameterizationService;
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
@RequestMapping("LoadClientStarParameterizationS/")
public class LoadClientStarParameterizationController {
    
     @Autowired
    private LoadClientStarParameterizationService LoadClientStarParameterizationS;
    
    @PostMapping(value = "create/{userId}")
    public ResponseEntity create(@RequestBody LoadClientStarParameterizationEntity l, @PathVariable int userId){
    ResponseModel response = this.LoadClientStarParameterizationS.create(l, userId);
    return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @PutMapping(value = "update/{userId}") //servicio actualizar
    public ResponseEntity update(@RequestBody LoadClientStarParameterizationEntity a, @PathVariable int userId) {
        ResponseModel response = this.LoadClientStarParameterizationS.update(a, userId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @DeleteMapping(value = "delete/{id}") //servicio eliminar
    public ResponseEntity delete(@PathVariable int id) {
        ResponseModel response = this.LoadClientStarParameterizationS.delete(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value = "list")
    public ResponseEntity list (){
    ResponseModel response = this.LoadClientStarParameterizationS.list();
    return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
   @GetMapping(value = "findById/{id}")
    public ResponseEntity findById(@PathVariable int id) {
        ResponseModel response = this.LoadClientStarParameterizationS.findById(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value = "listCustomer")
    public ResponseEntity listCustomer (){
    ResponseModel response = this.LoadClientStarParameterizationS.listCustomer();
    return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
