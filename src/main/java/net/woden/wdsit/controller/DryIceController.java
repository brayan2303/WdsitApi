/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.controller;

import net.woden.wdsit.entity.DryIceEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.DryIceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author j.hilarion
 */

@RestController
@RequestMapping("DryIceS/")
public class DryIceController {
    
       @Autowired
    private DryIceService DryIceS; 
    
    
     @PostMapping(value="create")
    public ResponseEntity create(@RequestBody DryIceEntity d){
        ResponseModel response=this.DryIceS.create(d); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value = "list")
    public ResponseEntity list() {
        ResponseModel response = this.DryIceS.list();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
     @PutMapping(value = "update") //servicio actualizar
    public ResponseEntity update() {
        ResponseModel response = this.DryIceS.update();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
     @GetMapping(value = "listFinish")
    public ResponseEntity listFinish() {
        ResponseModel response = this.DryIceS.listFinish();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    
}
