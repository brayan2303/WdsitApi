/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.controller;

import net.woden.wdsit.entity.GenHolidaysEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.GenHolidaysService;
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
@RequestMapping("GenHolidaysS/")
public class GenHolidaysController {
    
     @Autowired
    private GenHolidaysService GenHolidaysS;
    
    @PostMapping(value = "create") // servicio crear y almacenar
    public ResponseEntity create(@RequestBody GenHolidaysEntity g) {
        ResponseModel response = this.GenHolidaysS.create(g);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @PutMapping(value = "update") //servicio actualizar
    public ResponseEntity update(@RequestBody GenHolidaysEntity g) {
        ResponseModel response = this.GenHolidaysS.update(g);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping(value = "delete/{id}") //servicio eliminar
    public ResponseEntity delete(@PathVariable int id) {
        ResponseModel response = this.GenHolidaysS.delete(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "list") //servicio mostrar en pantalla
    public ResponseEntity list() {
        ResponseModel response = this.GenHolidaysS.list();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "findById/{id}")
    public ResponseEntity findById(@PathVariable int id) {
        ResponseModel response = this.GenHolidaysS.findById(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
