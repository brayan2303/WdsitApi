/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.controller;

import net.woden.wdsit.entity.ProFormulaVariableEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.ProFormulaVariableService;
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
 * @author b.algecira
 */
@RestController
@RequestMapping("proFormulaVariable/")
public class ProFormulaVariableController {
    
    @Autowired
    private ProFormulaVariableService proFormulaVariableS; 

    @PostMapping(value="create")
    public ResponseEntity create(@RequestBody ProFormulaVariableEntity b){
        ResponseModel response=this.proFormulaVariableS.create(b); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{formulaVariableId}")
    public ResponseEntity delete(@PathVariable int formulaVariableId){
        ResponseModel response=this.proFormulaVariableS.delete(formulaVariableId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list/{formulaId}")
    public ResponseEntity list(@PathVariable int formulaId){
        ResponseModel response=this.proFormulaVariableS.list(formulaId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="listMeasurementId/{measurementId}")
    public ResponseEntity listMeasurementId(@PathVariable int measurementId){
        ResponseModel response=this.proFormulaVariableS.listMeasurementId(measurementId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
