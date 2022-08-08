/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.controller;

import net.woden.wdsit.entity.ScpAuditPreviousEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.ScpAuditPreviousService;
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
@RequestMapping("scpAuditPrevious/")
public class ScpAuditPreviousController {
    
    @Autowired
    private ScpAuditPreviousService ScpAuditPreviousS; 
    
    @PostMapping(value="create/{userId}")
    public ResponseEntity create(@PathVariable int userId, @RequestBody ScpAuditPreviousEntity scp){
        ResponseModel response=this.ScpAuditPreviousS.create(userId, scp); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @GetMapping(value="findById/{auditPreviousId}")
    public ResponseEntity findById(@PathVariable int auditPreviousId){
        ResponseModel response=this.ScpAuditPreviousS.findById(auditPreviousId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @PutMapping(value="update/{auditPreviousId}")
    public ResponseEntity update(@PathVariable int auditPreviousId, @RequestBody ScpAuditPreviousEntity s){
        ResponseModel response=this.ScpAuditPreviousS.update(auditPreviousId,s); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="close/{auditPreviousId}")
    public ResponseEntity close(@PathVariable int auditPreviousId){
        ResponseModel response=this.ScpAuditPreviousS.close(auditPreviousId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{auditPreviousId}")
    public ResponseEntity delete(@PathVariable int auditPreviousId){
        ResponseModel response=this.ScpAuditPreviousS.delete(auditPreviousId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list")
    public ResponseEntity list(){
        ResponseModel response=this.ScpAuditPreviousS.list(); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="listForSerials")
    public ResponseEntity listForSerials(){
        ResponseModel response=this.ScpAuditPreviousS.listForSerials(); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="listAudit")
    public ResponseEntity listAudit(){
        ResponseModel response=this.ScpAuditPreviousS.listAudit(); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
