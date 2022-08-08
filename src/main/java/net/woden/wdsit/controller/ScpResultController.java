/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.woden.wdsit.controller;

import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.ScpResultService;
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
@RequestMapping("ScpResultS/")
public class ScpResultController {
    
    @Autowired
    private ScpResultService ScpResultS;

    
    
     @GetMapping(value = "resultCrossing/{id}") //servicio mostrar en pantalla
    public ResponseEntity findById(@PathVariable int id) {
        ResponseModel response = this.ScpResultS.resultCrossing(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
     @GetMapping(value = "resultSerial/{id}") //servicio mostrar en pantalla
    public ResponseEntity resultSerial(@PathVariable int id) {
        ResponseModel response = this.ScpResultS.resultSerial(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
     @GetMapping(value = "resultLevel/{id}") //servicio mostrar en pantalla
    public ResponseEntity resultLevel(@PathVariable int id) {
        ResponseModel response = this.ScpResultS.resultLevel(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    
}
