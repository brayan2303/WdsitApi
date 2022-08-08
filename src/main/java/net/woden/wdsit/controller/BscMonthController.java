package net.woden.wdsit.controller;

import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.BscMonthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("bscMonth/")
public class BscMonthController {
 
    @Autowired
    private BscMonthService bscMonthS; 

    @GetMapping(value="list/{measurementId}")
    public ResponseEntity list(@PathVariable int measurementId){
        ResponseModel response=this.bscMonthS.list(measurementId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="listMeasurement/{measurementId}")
    public ResponseEntity listMeasurement(@PathVariable int measurementId){
        ResponseModel response=this.bscMonthS.listMeasurement(measurementId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
