/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.controller;

import net.woden.wdsit.entity.ProAnalysisEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.ProAnalysisService;
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
 * @author b.algecira
 */
@RestController
@RequestMapping("proAnalysis/")
public class ProAnalysisController {
    
    @Autowired
    private ProAnalysisService proAnalysisS; 

    @PostMapping(value="create")
    public ResponseEntity create(@RequestBody ProAnalysisEntity b){
        ResponseModel response=this.proAnalysisS.create(b); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="update")
    public ResponseEntity update(@RequestBody ProAnalysisEntity b){
        ResponseModel response=this.proAnalysisS.update(b); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{analysisId}")
    public ResponseEntity delete(@PathVariable int analysisId){
        ResponseModel response=this.proAnalysisS.delete(analysisId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list/{measurementDetailId}")
    public ResponseEntity list(@PathVariable int measurementDetailId){
        ResponseModel response=this.proAnalysisS.list(measurementDetailId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="listMonth/{measurementId}/{monthId}")
    public ResponseEntity listMonth(@PathVariable int measurementId,@PathVariable int monthId){
        ResponseModel response=this.proAnalysisS.listMonth(measurementId,monthId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findById/{analysisId}")
    public ResponseEntity findById(@PathVariable int analysisId){
        ResponseModel response=this.proAnalysisS.findById(analysisId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
