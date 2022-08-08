/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.controller;

import java.util.ArrayList;
import net.woden.wdsit.entity.ScpAuditEntity;
import net.woden.wdsit.service.ScpAuditService;
import net.woden.wdsit.model.ResponseModel;
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
@RequestMapping("scpAudit/")
public class ScpAuditController {
    
    @Autowired
    private ScpAuditService ScpAuditS; 
    
    @PostMapping(value="create/{userId}")
    public ResponseEntity create(@PathVariable int userId, @RequestBody ScpAuditEntity scp){
        ResponseModel response=this.ScpAuditS.create(userId, scp); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="update/{auditId}")
    public ResponseEntity update(@PathVariable int auditId, @RequestBody ScpAuditEntity scp){
        ResponseModel response=this.ScpAuditS.update(auditId,  scp); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="close/{auditId}/{quantity}")
    public ResponseEntity close(@PathVariable int auditId, @PathVariable int quantity){
        ResponseModel response=this.ScpAuditS.close(auditId, quantity); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{auditId}")
    public ResponseEntity delete(@PathVariable int auditId){
        ResponseModel response=this.ScpAuditS.delete(auditId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list")
    public ResponseEntity list(){
        ResponseModel response=this.ScpAuditS.list(); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="listForPallet")
    public ResponseEntity listForPallet(){
        ResponseModel response=this.ScpAuditS.listForPallet(); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="listForCrossing")
    public ResponseEntity listForCrossing(){
        ResponseModel response=this.ScpAuditS.listForCrossing(); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findById/{auditId}")
    public ResponseEntity findById(@PathVariable int auditId){
        ResponseModel response=this.ScpAuditS.findById(auditId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @PutMapping(value="updateClose/{id}")
    public ResponseEntity updateClose(@PathVariable int id){
        ResponseModel response=this.ScpAuditS.updateClose(id); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="updateAuditApproved/{id}")
    public ResponseEntity updateAuditApproved(@PathVariable int id){
        ResponseModel response=this.ScpAuditS.updateAuditApproved(id); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="updateAuditRejected/{id}")
    public ResponseEntity updateAuditRejected(@PathVariable int id){
        ResponseModel response=this.ScpAuditS.updateAuditRejected(id); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
