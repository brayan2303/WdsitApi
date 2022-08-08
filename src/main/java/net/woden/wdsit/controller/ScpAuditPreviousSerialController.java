/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.controller;

import java.util.ArrayList;
import net.woden.wdsit.entity.ScpAuditPreviousSerialEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.ScpAuditPreviousSerialService;
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
 * @author m.pulido
 */
@RestController
@RequestMapping("scpAuditPreviousSerial/")
public class ScpAuditPreviousSerialController {
    
    @Autowired
    private ScpAuditPreviousSerialService ScpAuditPreviousSerialS; 
    
    @PostMapping(value="create/{userId}")
    public ResponseEntity create(@PathVariable int userId, @PathVariable int auditPreviousId, @RequestBody ScpAuditPreviousSerialEntity scp){
        ResponseModel response=this.ScpAuditPreviousSerialS.create(userId, auditPreviousId, scp); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PostMapping(value="createAll/{userId}/{auditPreviousId}")
    public ResponseEntity createAll(@PathVariable int userId, @PathVariable int auditPreviousId, @RequestBody ArrayList<ScpAuditPreviousSerialEntity> scp){
        ResponseModel response=this.ScpAuditPreviousSerialS.createAll(userId, auditPreviousId, scp); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{auditPreviousId}")
    public ResponseEntity delete(@PathVariable int auditPreviousId){
        ResponseModel response=this.ScpAuditPreviousSerialS.delete(auditPreviousId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list/{auditPreviousId}")
    public ResponseEntity list(@PathVariable int auditPreviousId){
        ResponseModel response=this.ScpAuditPreviousSerialS.list(auditPreviousId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findBySerial/{serial}/{cliente}")
    public ResponseEntity list(@PathVariable String serial, @PathVariable String cliente){
        ResponseModel response=this.ScpAuditPreviousSerialS.findBySerial(serial, cliente); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
