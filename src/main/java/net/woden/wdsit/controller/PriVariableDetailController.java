/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.controller;

import java.util.ArrayList;
import net.woden.wdsit.entity.PriVariableDetailEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.PriVariableDetailService;
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
 * @author b.algecira
 */
@RestController
@RequestMapping("PriVariableDetailS/")
public class PriVariableDetailController {
    
       @Autowired
    private PriVariableDetailService PriVariableDetailS; 

    @PostMapping(value="create")
    public ResponseEntity create(@RequestBody ArrayList<PriVariableDetailEntity> array){
        ResponseModel response=this.PriVariableDetailS.create(array); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="update")
    public ResponseEntity update(@RequestBody ArrayList<PriVariableDetailEntity> array){
        ResponseModel response=this.PriVariableDetailS.update(array); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list/{varibleFormId}")
    public ResponseEntity list(@PathVariable int varibleFormId){
        ResponseModel response=this.PriVariableDetailS.list(varibleFormId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
