package net.woden.wdsit.controller;

import net.woden.wdsit.entity.BscMeasurementEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.BscMeasurementService;
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

@RestController
@RequestMapping("bscMeasurement/")
public class BscMeasurementController {
 
    @Autowired
    private BscMeasurementService bscMeasurementS; 

    @PostMapping(value="create")
    public ResponseEntity create(@RequestBody BscMeasurementEntity b){
        ResponseModel response=this.bscMeasurementS.create(b); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="update")
    public ResponseEntity update(@RequestBody BscMeasurementEntity b){
        ResponseModel response=this.bscMeasurementS.update(b); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{measurementId}")
    public ResponseEntity delete(@PathVariable int measurementId){
        ResponseModel response=this.bscMeasurementS.delete(measurementId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list/{indicatorId}/{personId}")
    public ResponseEntity list(@PathVariable int indicatorId,@PathVariable int personId){
        ResponseModel response=this.bscMeasurementS.list(indicatorId,personId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="listActive/{indicatorId}/{personId}")
    public ResponseEntity listActive(@PathVariable int indicatorId,@PathVariable int personId){
        ResponseModel response=this.bscMeasurementS.listActive(indicatorId,personId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
