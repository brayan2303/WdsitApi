package net.woden.wdsit.controller;

import net.woden.wdsit.entity.GenCountryEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.GenCountryService;
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
@RequestMapping("genCountry/")
public class GenCountryController {
 
    @Autowired
    private GenCountryService genCountryS; 

    @PostMapping(value="create")
    public ResponseEntity create(@RequestBody GenCountryEntity g){
        ResponseModel response=this.genCountryS.create(g); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="update")
    public ResponseEntity update(@RequestBody GenCountryEntity g){
        ResponseModel response=this.genCountryS.update(g); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{countryId}")
    public ResponseEntity update(@PathVariable int countryId){
        ResponseModel response=this.genCountryS.delete(countryId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findById/{countryId}")
    public ResponseEntity findById(@PathVariable int countryId){
        ResponseModel response=this.genCountryS.findById(countryId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list")
    public ResponseEntity list(){
        ResponseModel response=this.genCountryS.list(); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="listActive")
    public ResponseEntity listActive(){
        ResponseModel response=this.genCountryS.listActive(); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
