/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.controller;

import net.woden.wdsit.entity.ScpAuditStateTypeEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.ScpAuditStateTypeService;
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
@RequestMapping("scpAuditStateType/")
public class ScpAuditStateTypeController {
    
    @Autowired
    private ScpAuditStateTypeService ScpAuditStateTypeS; 
    
    @PostMapping(value="create/{userId}")
    public ResponseEntity create(@PathVariable int userId, @RequestBody ScpAuditStateTypeEntity scp){
        ResponseModel response=this.ScpAuditStateTypeS.create(userId, scp); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @GetMapping(value="findById/{auditStateTypeId}")
    public ResponseEntity findById(@PathVariable int auditStateTypeId){
        ResponseModel response=this.ScpAuditStateTypeS.findById(auditStateTypeId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @PutMapping(value="update/{auditStateTypeId}")
    public ResponseEntity update(@PathVariable int auditStateTypeId, @RequestBody ScpAuditStateTypeEntity s){
        ResponseModel response=this.ScpAuditStateTypeS.update(auditStateTypeId,s); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{auditStateTypeId}")
    public ResponseEntity delete(@PathVariable int auditStateTypeId){
        ResponseModel response=this.ScpAuditStateTypeS.delete(auditStateTypeId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list")
    public ResponseEntity list(){
        ResponseModel response=this.ScpAuditStateTypeS.list(); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
