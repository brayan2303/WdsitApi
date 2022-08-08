/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.controller;

import net.woden.wdsit.entity.ActiveFixedExitEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.ActiveFixedExitService;
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
@RequestMapping("ActiveFixedExit/")
public class ActiveFixedExitController {

    @Autowired
    private ActiveFixedExitService ActiveFixedExit;

    @PostMapping(value = "create") // servicio crear y almacenar
    public ResponseEntity create(@RequestBody ActiveFixedExitEntity a) {
        ResponseModel response = this.ActiveFixedExit.create(a);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping(value = "delete/{id}") //servicio eliminar
    public ResponseEntity delete(@PathVariable int id) {
        ResponseModel response = this.ActiveFixedExit.delete(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "list") //servicio mostrar en pantalla
    public ResponseEntity list() {
        ResponseModel response = this.ActiveFixedExit.list();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
     
    @PutMapping(value = "updateObservation") //servicio actualizar
    public ResponseEntity updateObservation(@RequestBody ActiveFixedExitEntity a) {
        ResponseModel response = this.ActiveFixedExit.updateObservation(a);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
