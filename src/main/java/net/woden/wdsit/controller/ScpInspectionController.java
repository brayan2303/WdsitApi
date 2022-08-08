/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.woden.wdsit.controller;

import net.woden.wdsit.entity.ScpInspectionEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.ScpInspectionService;
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
@RequestMapping("ScpInspectionS/")
public class ScpInspectionController {

    @Autowired
    private ScpInspectionService ScpInspectionS;

    @PostMapping(value = "create/{userId}")
    public ResponseEntity create(@RequestBody ScpInspectionEntity s, @PathVariable int userId) {
        ResponseModel response = this.ScpInspectionS.create(s,userId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "list/{auditId}") //servicio mostrar en pantalla
    public ResponseEntity list(@PathVariable int auditId) {
        ResponseModel response = this.ScpInspectionS.list(auditId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
        @GetMapping(value = "findById/{id}") //servicio mostrar en pantalla
    public ResponseEntity findById(@PathVariable int id) {
        ResponseModel response = this.ScpInspectionS.findById(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping(value = "delete/{id}") //servicio eliminar
    public ResponseEntity delete(@PathVariable int id) {
        ResponseModel response = this.ScpInspectionS.delete(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping(value = "update/{userIdupdate}")
    public ResponseEntity update(@RequestBody ScpInspectionEntity s, @PathVariable int userIdupdate) {
        ResponseModel response = this.ScpInspectionS.update(s, userIdupdate);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
