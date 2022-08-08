/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.controller;

import net.woden.wdsit.entity.PqrFilesCategoryEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.PqrFilesCategoryService;
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
@RequestMapping("PqrFilesCategoryS/")
public class PqrFilesCategoryController {
    
      @Autowired
    private PqrFilesCategoryService PqrFilesCategoryS;

    @PostMapping(value = "create/{countryId}") // servicio crear y almacenar
    public ResponseEntity create(@RequestBody PqrFilesCategoryEntity p, @PathVariable int countryId) {
        ResponseModel response = this.PqrFilesCategoryS.create(p,countryId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping(value = "update") //servicio actualizar
    public ResponseEntity update(@RequestBody PqrFilesCategoryEntity p) {
        ResponseModel response = this.PqrFilesCategoryS.update(p);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping(value = "delete/{id}") //servicio eliminar
    public ResponseEntity delete(@PathVariable int id) {
        ResponseModel response = this.PqrFilesCategoryS.delete(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "list/{typeClient}/{countryId}") //servicio mostrar en pantalla
    public ResponseEntity list(@PathVariable String typeClient, @PathVariable int countryId) {
        ResponseModel response = this.PqrFilesCategoryS.list(typeClient,countryId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value = "listAll/{countryId}") //servicio mostrar en pantalla
    public ResponseEntity listAll(@PathVariable int countryId) {
        ResponseModel response = this.PqrFilesCategoryS.listAll(countryId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "findById/{id}")
    public ResponseEntity findById(@PathVariable int id) {
        ResponseModel response = this.PqrFilesCategoryS.findById(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    
}
