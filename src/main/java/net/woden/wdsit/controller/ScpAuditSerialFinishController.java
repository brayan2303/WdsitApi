/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.woden.wdsit.controller;

import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.ScpAuditSerialFinishService;
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
@RequestMapping("ScpAuditSerialFinishS/")
public class ScpAuditSerialFinishController {
    
     @Autowired
    private ScpAuditSerialFinishService ScpAuditSerialFinishS;
    
    
     @GetMapping(value = "list") //servicio mostrar en pantalla
    public ResponseEntity list() {
        ResponseModel response = this.ScpAuditSerialFinishS.list();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
     @GetMapping(value = "listCount/{levelId}") //servicio mostrar en pantalla
    public ResponseEntity listCount(@PathVariable int levelId) {
        ResponseModel response = this.ScpAuditSerialFinishS.listCount(levelId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @GetMapping(value = "listAudit") //servicio mostrar en pantalla
    public ResponseEntity listAudit() {
        ResponseModel response = this.ScpAuditSerialFinishS.listAudit();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
     @GetMapping(value = "listMotif") //servicio mostrar en pantalla
    public ResponseEntity listMotif() {
        ResponseModel response = this.ScpAuditSerialFinishS.listMotif();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
      @GetMapping(value = "listLevel/{levelId}") //servicio mostrar en pantalla
    public ResponseEntity listLevel(@PathVariable int levelId) {
        ResponseModel response = this.ScpAuditSerialFinishS.listLevel(levelId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
     @GetMapping(value = "listAuditSerial/{id}") //servicio mostrar en pantalla
    public ResponseEntity listAuditSerial(@PathVariable int id) {
        ResponseModel response = this.ScpAuditSerialFinishS.listAuditSerial(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
