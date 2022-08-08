/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.controller;

import net.woden.wdsit.entity.ScpAuditMotifEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.ScpAuditMotifService;
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
@RequestMapping("scpAuditMotif/")
public class ScpAuditMotifController {
    
    @Autowired
    private ScpAuditMotifService ScpAuditMotifS; 
    
    @PostMapping(value="create/{userId}")
    public ResponseEntity create(@PathVariable int userId, @RequestBody ScpAuditMotifEntity scp){
        ResponseModel response=this.ScpAuditMotifS.create(userId, scp); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @GetMapping(value="findById/{auditMotifId}")
    public ResponseEntity findById(@PathVariable int auditMotifId){
        ResponseModel response=this.ScpAuditMotifS.findById(auditMotifId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @PutMapping(value="update/{auditMotifId}")
    public ResponseEntity update(@PathVariable int auditMotifId, @RequestBody ScpAuditMotifEntity s){
        ResponseModel response=this.ScpAuditMotifS.update(auditMotifId,s); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{auditMotifId}")
    public ResponseEntity delete(@PathVariable int auditMotifId){
        ResponseModel response=this.ScpAuditMotifS.delete(auditMotifId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list")
    public ResponseEntity list(){
        ResponseModel response=this.ScpAuditMotifS.list(); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
