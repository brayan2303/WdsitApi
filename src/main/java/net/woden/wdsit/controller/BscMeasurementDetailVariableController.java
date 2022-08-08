package net.woden.wdsit.controller;

import java.util.ArrayList;
import net.woden.wdsit.entity.BscMeasurementDetailVariableEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.BscMeasurementDetailVariableService;
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

@RestController
@RequestMapping("bscMeasurementDetailVariable/")
public class BscMeasurementDetailVariableController {
 
    @Autowired
    private BscMeasurementDetailVariableService bscMeasurementDetailVariableS; 

    @PostMapping(value="create")
    public ResponseEntity create(@RequestBody ArrayList<BscMeasurementDetailVariableEntity> array){
        ResponseModel response=this.bscMeasurementDetailVariableS.create(array); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="update")
    public ResponseEntity update(@RequestBody ArrayList<BscMeasurementDetailVariableEntity> array){
        ResponseModel response=this.bscMeasurementDetailVariableS.update(array); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list/{measurementDetailId}")
    public ResponseEntity list(@PathVariable int measurementDetailId){
        ResponseModel response=this.bscMeasurementDetailVariableS.list(measurementDetailId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
