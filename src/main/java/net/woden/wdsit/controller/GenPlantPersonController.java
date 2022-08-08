/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.controller;

import net.woden.wdsit.entity.GenPlantPersonEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.GenPlantPersonService;
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
@RequestMapping("GenPlantPerson/")
public class GenPlantPersonController {
    
     @Autowired
    private GenPlantPersonService GenPlantPerson;

    @PostMapping(value = "create") // servicio crear y almacenar
    public ResponseEntity create(@RequestBody GenPlantPersonEntity g) {
        ResponseModel response = this.GenPlantPerson.create(g);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping(value = "delete/{personId}/{plantId}")
    public ResponseEntity delete(@PathVariable int personId, @PathVariable int plantId) {
        ResponseModel response = this.GenPlantPerson.delete(personId, plantId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "list") //servicio mostrar en pantalla
    public ResponseEntity list() {
        ResponseModel response = this.GenPlantPerson.list();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "findAll/{personId}")
    public ResponseEntity findAll(@PathVariable int personId) {
        ResponseModel response = this.GenPlantPerson.findAll(personId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    
}
