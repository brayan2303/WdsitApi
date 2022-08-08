/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.controller;

import net.woden.wdsit.entity.GenPlantEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.GenPlantService;
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
@RequestMapping("GenPlant/")
public class GenPlantController {
    
     @Autowired
    private GenPlantService GenPlant;

    @PostMapping(value = "create") // servicio crear y almacenar
    public ResponseEntity create(@RequestBody GenPlantEntity g) {
        ResponseModel response = this.GenPlant.create(g);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping(value = "update")
    public ResponseEntity update(@RequestBody GenPlantEntity g) {
        ResponseModel response = this.GenPlant.update(g);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping(value = "delete/{id}")
    public ResponseEntity delete(@PathVariable int id) {
        ResponseModel response = this.GenPlant.delete(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "list") //servicio mostrar en pantalla
    public ResponseEntity list() {
        ResponseModel response = this.GenPlant.list();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
     @GetMapping("findById/{plantId}")
    public ResponseEntity findById(@PathVariable int plantId){
        ResponseModel response=this.GenPlant.findById(plantId);
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
      @GetMapping("findByUserId/{userId}/{countryId}")
    public ResponseEntity findByUserId(@PathVariable int userId, @PathVariable int countryId){
        ResponseModel response=this.GenPlant.findByUserId(userId, countryId);
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
