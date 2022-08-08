package net.woden.wdsit.controller;

import net.woden.wdsit.entity.BscMeasurementDetailEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.BscMeasurementDetailService;
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

@RestController
@RequestMapping("bscMeasurementDetail/")
public class BscMeasurementDetailController {
 
    @Autowired
    private BscMeasurementDetailService bscMeasurementDetailS; 

    @PostMapping(value="create")
    public ResponseEntity create(@RequestBody BscMeasurementDetailEntity b){
        ResponseModel response=this.bscMeasurementDetailS.create(b); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="update/{id}/{type}/{value}")
    public ResponseEntity update(@PathVariable int id,@PathVariable String type,@PathVariable double value){
        ResponseModel response=this.bscMeasurementDetailS.update(id,type,value); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{measurementDetailId}")
    public ResponseEntity delete(@PathVariable int measurementDetailId){
        ResponseModel response=this.bscMeasurementDetailS.delete(measurementDetailId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="openClose/{measurementDetailId}/{status}")
    public ResponseEntity openClose(@PathVariable int measurementDetailId,@PathVariable String status){
        ResponseModel response=this.bscMeasurementDetailS.openClose(measurementDetailId,status); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list/{measurementId}")
    public ResponseEntity list(@PathVariable int measurementId){
        ResponseModel response=this.bscMeasurementDetailS.list(measurementId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="listClose/{measurementId}")
    public ResponseEntity listClose(@PathVariable int measurementId){
        ResponseModel response=this.bscMeasurementDetailS.listClose(measurementId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PostMapping(value="loadFile/{measurementId}/{measurementDetailId}")
    public ResponseEntity loadFile(@PathVariable int measurementId,@PathVariable int measurementDetailId,@RequestParam("files") MultipartFile[] files){
        ResponseModel response=this.bscMeasurementDetailS.loadFile(measurementId,measurementDetailId,files); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="listFile/{measurementId}/{measurementDetailId}")
    public ResponseEntity listFile(@PathVariable int measurementId,@PathVariable int measurementDetailId){
        ResponseModel response=this.bscMeasurementDetailS.listFile(measurementId,measurementDetailId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="deleteFile/{measurementId}/{measurementDetailId}/{fileName}")
    public ResponseEntity deleteFile(@PathVariable int measurementId,@PathVariable int measurementDetailId,@PathVariable String fileName){
        ResponseModel response=this.bscMeasurementDetailS.deleteFile(measurementId,measurementDetailId,fileName); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="deleteFileMeasurement/{measurementId}")
    public ResponseEntity deleteFileMeasurement(@PathVariable int measurementId){
        ResponseModel response=this.bscMeasurementDetailS.deleteFileMeasurement(measurementId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
