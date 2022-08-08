package net.woden.wdsit.controller;

import net.woden.wdsit.entity.BscPerspectiveEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.BscPerspectiveService;
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
@RequestMapping("bscPerspective/")
public class BscPerspectiveController {
 
    @Autowired
    private BscPerspectiveService bscPerspectiveS; 

    @PostMapping(value="create")
    public ResponseEntity create(@RequestBody BscPerspectiveEntity b){
        ResponseModel response=this.bscPerspectiveS.create(b); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="update")
    public ResponseEntity update(@RequestBody BscPerspectiveEntity b){
        ResponseModel response=this.bscPerspectiveS.update(b); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{perspectiveId}")
    public ResponseEntity delete(@PathVariable int perspectiveId){
        ResponseModel response=this.bscPerspectiveS.delete(perspectiveId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list/{year}/{countryId}")
    public ResponseEntity list(@PathVariable int year,@PathVariable int countryId){
        ResponseModel response=this.bscPerspectiveS.list(year,countryId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="listActive/{year}/{personId}/{countryId}")
    public ResponseEntity listActive(@PathVariable int year,@PathVariable int personId,@PathVariable int countryId){
        ResponseModel response=this.bscPerspectiveS.listActive(year,personId,countryId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="total/{year}/{countryId}")
    public ResponseEntity total(@PathVariable int year,@PathVariable int countryId){
        ResponseModel response=this.bscPerspectiveS.total(year,countryId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="percentage/{year}")
    public ResponseEntity percentage(@PathVariable int year){
        ResponseModel response=this.bscPerspectiveS.percentage(year); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="percentageMonth/{perspectiveId}")
    public ResponseEntity percentageMonth(@PathVariable int perspectiveId){
        ResponseModel response=this.bscPerspectiveS.percentageMonth(perspectiveId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
