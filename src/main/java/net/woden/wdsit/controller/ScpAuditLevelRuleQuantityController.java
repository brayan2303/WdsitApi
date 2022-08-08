/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.controller;

import net.woden.wdsit.entity.ScpAuditLevelRuleQuantityEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.ScpAuditLevelRuleQuantityService;
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
@RequestMapping("ScpAuditLevelRuleQuantity/")
public class ScpAuditLevelRuleQuantityController {
    
    @Autowired
    private ScpAuditLevelRuleQuantityService ScpAuditLevelRuleQuantityS; 
    
    @PostMapping(value="create/{userId}")
    public ResponseEntity create(@PathVariable int userId, @RequestBody ScpAuditLevelRuleQuantityEntity scp){
        ResponseModel response=this.ScpAuditLevelRuleQuantityS.create(userId, scp); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findById/{levelRuleQuantityId}")
    public ResponseEntity getById(@PathVariable int levelRuleQuantityId){
        ResponseModel response=this.ScpAuditLevelRuleQuantityS.findById(levelRuleQuantityId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="update/{levelRuleQuantityId}")
    public ResponseEntity update(@PathVariable int levelRuleQuantityId, @RequestBody ScpAuditLevelRuleQuantityEntity s){
        ResponseModel response=this.ScpAuditLevelRuleQuantityS.update(levelRuleQuantityId,s); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{levelRuleQuantityId}")
    public ResponseEntity delete(@PathVariable int levelRuleQuantityId){
        ResponseModel response=this.ScpAuditLevelRuleQuantityS.delete(levelRuleQuantityId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list")
    public ResponseEntity list(){
        ResponseModel response=this.ScpAuditLevelRuleQuantityS.list(); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
