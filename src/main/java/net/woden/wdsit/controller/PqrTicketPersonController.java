/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.woden.wdsit.controller;

import net.woden.wdsit.entity.PqrTicketPersonEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.PqrTicketPersonService;
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
@RequestMapping("PqrTicketPersonS/")
public class PqrTicketPersonController {
    
    
        @Autowired
    private PqrTicketPersonService PqrTicketPersonS;

    @PostMapping(value = "create") // servicio crear y almacenar
    public ResponseEntity create(@RequestBody PqrTicketPersonEntity d) {
        ResponseModel response = this.PqrTicketPersonS.create(d);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping(value = "delete/{userId}/{pqrTicketId}")
    public ResponseEntity delete(@PathVariable int userId, @PathVariable int pqrTicketId) {
        ResponseModel response = this.PqrTicketPersonS.delete(userId, pqrTicketId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "list")
    public ResponseEntity list() {
        ResponseModel response = this.PqrTicketPersonS.list();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "findAll/{id}")
    public ResponseEntity findAll(@PathVariable int id) {
        ResponseModel response = this.PqrTicketPersonS.findAll(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
     @GetMapping(value = "listUserId/{userId}")
    public ResponseEntity listUserId(@PathVariable int userId) {
        ResponseModel response = this.PqrTicketPersonS.listUserId(userId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
