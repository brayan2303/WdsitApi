/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.controller;

import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.wtsPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author b.algecira
 */
@RestController
@RequestMapping("wtsp/")
public class wtsPcontroller {
    
    @Autowired
    private wtsPService wtsp;
    
    @GetMapping(value = "listPhone/{phone}") //servicio mostrar en pantalla
    public ResponseEntity listPhone(@PathVariable String phone) {
        ResponseModel response = this.wtsp.listPhone(phone);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
     @GetMapping(value = "listDocument/{document}") //servicio mostrar en pantalla
    public ResponseEntity listDocument(@PathVariable String document) {
        ResponseModel response = this.wtsp.listDocument(document);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
     @GetMapping(value = "listSerial/{serial}") //servicio mostrar en pantalla
    public ResponseEntity listSerial(@PathVariable String serial) {
        ResponseModel response = this.wtsp.listSerial(serial);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    
}
