/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.woden.wdsit.controller;

import net.woden.wdsit.entity.PqrPageClientInitEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.PqrPageClientInitService;
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
@RequestMapping("PqrPageClientInitS/")
public class PqrPageClientInitController {

    @Autowired
    private PqrPageClientInitService PqrPageClientInitS;

    @PostMapping(value = "create/{countryId}")
    public ResponseEntity create(@RequestBody PqrPageClientInitEntity a, @PathVariable int countryId) {
        ResponseModel response = this.PqrPageClientInitS.create(a,countryId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping(value = "update") //servicio actualizar
    public ResponseEntity update(@RequestBody PqrPageClientInitEntity a) {
        ResponseModel response = this.PqrPageClientInitS.update(a);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping(value = "delete/{id}") //servicio eliminar
    public ResponseEntity delete(@PathVariable int id) {
        ResponseModel response = this.PqrPageClientInitS.delete(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "list/{countryId}")
    public ResponseEntity list(@PathVariable int countryId) {
        ResponseModel response = this.PqrPageClientInitS.list(countryId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "findById/{id}")
    public ResponseEntity findById(@PathVariable int id) {
        ResponseModel response = this.PqrPageClientInitS.findById(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
      @GetMapping(value = "findByUserId/{id}")
    public ResponseEntity findByUserId(@PathVariable int id) {
        ResponseModel response = this.PqrPageClientInitS.findByUserId(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

}