/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.controller;

import java.util.ArrayList;
import net.woden.wdsit.entity.ActiveFixedAssigSerialEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.ActiveFixedAssigSerialService;
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
@RequestMapping("ActiveFixedAssigSerial/")
public class ActiveFixedAssigSerialController {
    
     @Autowired
    private ActiveFixedAssigSerialService ActiveFixedAssigSerial;

    @PostMapping(value = "create") // servicio crear y almacenar
    public ResponseEntity create(@RequestBody ArrayList<ActiveFixedAssigSerialEntity> a) {
        ResponseModel response = this.ActiveFixedAssigSerial.create(a);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value = "update") 
     public ResponseEntity update(@RequestBody ActiveFixedAssigSerialEntity a) {
        ResponseModel response = this.ActiveFixedAssigSerial.update(a);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

     @DeleteMapping(value = "delete/{assigmentId}")
    public ResponseEntity delete(@PathVariable int assigmentId) {
        ResponseModel response = this.ActiveFixedAssigSerial.delete(assigmentId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "list") //servicio mostrar en pantalla
    public ResponseEntity list() {
        ResponseModel response = this.ActiveFixedAssigSerial.list();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    
}
