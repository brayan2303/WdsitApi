package net.woden.wdsit.controller;

import net.woden.wdsit.entity.BscStrategicObjetiveEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.BscStrategicObjetiveService;
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
@RequestMapping("bscStrategicObjetive/")
public class BscStrategicObjetiveController {
 
    @Autowired
    private BscStrategicObjetiveService bscStrategicObjetiveS; 

    @PostMapping(value="create")
    public ResponseEntity create(@RequestBody BscStrategicObjetiveEntity b){
        ResponseModel response=this.bscStrategicObjetiveS.create(b); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="update")
    public ResponseEntity update(@RequestBody BscStrategicObjetiveEntity b){
        ResponseModel response=this.bscStrategicObjetiveS.update(b); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{strategicObjetiveId}")
    public ResponseEntity delete(@PathVariable int strategicObjetiveId){
        ResponseModel response=this.bscStrategicObjetiveS.delete(strategicObjetiveId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list/{perspectiveId}")
    public ResponseEntity list(@PathVariable int perspectiveId){
        ResponseModel response=this.bscStrategicObjetiveS.list(perspectiveId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="listActive/{perspectiveId}")
    public ResponseEntity listActive(@PathVariable int perspectiveId){
        ResponseModel response=this.bscStrategicObjetiveS.listActive(perspectiveId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="percentage/{perspectiveId}")
    public ResponseEntity percentage(@PathVariable int perspectiveId){
        ResponseModel response=this.bscStrategicObjetiveS.percentage(perspectiveId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="percentageMonth/{strategicObjetiveId}")
    public ResponseEntity percentageMonth(@PathVariable int strategicObjetiveId){
        ResponseModel response=this.bscStrategicObjetiveS.percentageMonth(strategicObjetiveId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="total/{perspectiveId}")
    public ResponseEntity total(@PathVariable int perspectiveId){
        ResponseModel response=this.bscStrategicObjetiveS.total(perspectiveId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
