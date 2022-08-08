package net.woden.wdsit.controller;

import net.woden.wdsit.entity.BscIndicatorEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.BscIndicatorService;
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
@RequestMapping("bscIndicator/")
public class BscIndicatorController {
 
    @Autowired
    private BscIndicatorService bscIndicatorS; 

    @PostMapping(value="create")
    public ResponseEntity create(@RequestBody BscIndicatorEntity b){
        ResponseModel response=this.bscIndicatorS.create(b); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="update")
    public ResponseEntity update(@RequestBody BscIndicatorEntity b){
        ResponseModel response=this.bscIndicatorS.update(b); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{indicatorId}")
    public ResponseEntity delete(@PathVariable int indicatorId){
        ResponseModel response=this.bscIndicatorS.delete(indicatorId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list/{strategicObjetiveId}")
    public ResponseEntity list(@PathVariable int strategicObjetiveId){
        ResponseModel response=this.bscIndicatorS.list(strategicObjetiveId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="listActive/{strategicObjetiveId}")
    public ResponseEntity listActive(@PathVariable int strategicObjetiveId){
        ResponseModel response=this.bscIndicatorS.listActive(strategicObjetiveId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="percentage/{strategicObjetiveId}")
    public ResponseEntity percentage(@PathVariable int strategicObjetiveId){
        ResponseModel response=this.bscIndicatorS.percentage(strategicObjetiveId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="percentageMonth/{indicatorId}")
    public ResponseEntity percentageMonth(@PathVariable int indicatorId){
        ResponseModel response=this.bscIndicatorS.percentageMonth(indicatorId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="total/{strategicObjetiveId}")
    public ResponseEntity total(@PathVariable int strategicObjetiveId){
        ResponseModel response=this.bscIndicatorS.total(strategicObjetiveId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
