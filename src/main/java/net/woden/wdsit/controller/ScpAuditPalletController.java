/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.controller;

import java.util.ArrayList;
import net.woden.wdsit.entity.ScpAuditPalletEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.ScpAuditPalletService;
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
 * @author m.pulido
 */
@RestController
@RequestMapping("scpAuditPallet/")
public class ScpAuditPalletController {
 
    @Autowired
    private ScpAuditPalletService ScpAuditPalletS; 
    
    @PostMapping(value="create/{userId}")
    public ResponseEntity create(@PathVariable int userId, @RequestBody ScpAuditPalletEntity scp){
        ResponseModel response=this.ScpAuditPalletS.create(userId, scp); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PostMapping(value="createAll/{userId}/{auditId}")
    public ResponseEntity createAll(@PathVariable int userId, @PathVariable int auditId, @RequestBody ArrayList<ScpAuditPalletEntity> scp){
        ResponseModel response=this.ScpAuditPalletS.createAll(userId, auditId, scp); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{auditId}")
    public ResponseEntity delete(@PathVariable int auditId){
        ResponseModel response=this.ScpAuditPalletS.delete(auditId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list")
    public ResponseEntity list(){
        ResponseModel response=this.ScpAuditPalletS.list(); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findById/{auditPalletId}")
    public ResponseEntity findById(@PathVariable int auditPalletId){
        ResponseModel response=this.ScpAuditPalletS.findById(auditPalletId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findByAuditId/{auditId}")
    public ResponseEntity findByAuditId(@PathVariable int auditId){
        ResponseModel response=this.ScpAuditPalletS.findByAuditId(auditId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="search/pallet/{pallet}/{cliente}")
    public ResponseEntity findByPalletWMS(@PathVariable String pallet, @PathVariable String cliente){
        ResponseModel response=this.ScpAuditPalletS.findByPalletWMS(pallet, cliente); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @GetMapping(value="search/pallet/wms/{pallet}/{customer}")
    public ResponseEntity findSerialsByPalletWMS(@PathVariable String pallet, @PathVariable String customer){
        ResponseModel response=this.ScpAuditPalletS.findSerialsByPalletWMS(pallet,customer); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
