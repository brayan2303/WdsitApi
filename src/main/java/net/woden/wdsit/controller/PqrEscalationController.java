/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.controller;

import net.woden.wdsit.entity.PqrEscalationEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.PqrEscalationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author b.algecira
 */
@RestController
@RequestMapping("PqrEscalation/")
public class PqrEscalationController {
    
    
      @Autowired
    private PqrEscalationService PqrEscalation;

    @PostMapping(value = "create")
    public ResponseEntity create(@RequestBody PqrEscalationEntity p) {
        ResponseModel response = this.PqrEscalation.create(p);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
      @GetMapping(value = "list")
    public ResponseEntity list() {
        ResponseModel response = this.PqrEscalation.list();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
      @GetMapping(value = "findById/{number}")
    public ResponseEntity findById(@PathVariable String number) {
        ResponseModel response = this.PqrEscalation.findById(number);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
     @PostMapping("sendEmail")
    public ResponseEntity sendEmail(@RequestParam String id,@RequestParam String number,@RequestParam int remitenteId){
        ResponseModel response=this.PqrEscalation.sendEmail(id,number,remitenteId);
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
