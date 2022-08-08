/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.controller;

import net.woden.wdsit.entity.ProMeasurementDetailEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.ProMeasurementDetailService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author b.algecira
 */
@RestController
@RequestMapping("proMeasurementDetail/")
public class ProMeasurementDetailController {
    
    @Autowired
    private ProMeasurementDetailService proMeasurementDetailS; 

    @PostMapping(value="create")
    public ResponseEntity create(@RequestBody ProMeasurementDetailEntity b){
        ResponseModel response=this.proMeasurementDetailS.create(b); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="update/{id}/{type}/{value}")
    public ResponseEntity update(@PathVariable int id,@PathVariable String type,@PathVariable double value){
        ResponseModel response=this.proMeasurementDetailS.update(id,type,value); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{measurementDetailId}")
    public ResponseEntity delete(@PathVariable int measurementDetailId){
        ResponseModel response=this.proMeasurementDetailS.delete(measurementDetailId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="openClose/{measurementDetailId}/{status}")
    public ResponseEntity openClose(@PathVariable int measurementDetailId,@PathVariable String status){
        ResponseModel response=this.proMeasurementDetailS.openClose(measurementDetailId,status); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list/{measurementId}")
    public ResponseEntity list(@PathVariable int measurementId){
        ResponseModel response=this.proMeasurementDetailS.list(measurementId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="listClose/{measurementId}")
    public ResponseEntity listClose(@PathVariable int measurementId){
        ResponseModel response=this.proMeasurementDetailS.listClose(measurementId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PostMapping(value="loadFile/{measurementId}/{measurementDetailId}")
    public ResponseEntity loadFile(@PathVariable int measurementId,@PathVariable int measurementDetailId,@RequestParam("files") MultipartFile[] files){
        ResponseModel response=this.proMeasurementDetailS.loadFile(measurementId,measurementDetailId,files); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="listFile/{measurementId}/{measurementDetailId}")
    public ResponseEntity listFile(@PathVariable int measurementId,@PathVariable int measurementDetailId){
        ResponseModel response=this.proMeasurementDetailS.listFile(measurementId,measurementDetailId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="deleteFile/{measurementId}/{measurementDetailId}/{fileName}")
    public ResponseEntity deleteFile(@PathVariable int measurementId,@PathVariable int measurementDetailId,@PathVariable String fileName){
        ResponseModel response=this.proMeasurementDetailS.deleteFile(measurementId,measurementDetailId,fileName); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="deleteFileMeasurement/{measurementId}")
    public ResponseEntity deleteFileMeasurement(@PathVariable int measurementId){
        ResponseModel response=this.proMeasurementDetailS.deleteFileMeasurement(measurementId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
