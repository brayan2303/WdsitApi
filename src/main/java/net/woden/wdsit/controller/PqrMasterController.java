package net.woden.wdsit.controller;

import net.woden.wdsit.entity.PqrMasterEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.PqrMasterService;
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
@RequestMapping("pqrMaster/")
public class PqrMasterController {
 
    @Autowired
    private PqrMasterService pqrMasterS; 

    @PostMapping(value="create/{countryId}")
    public ResponseEntity create(@RequestBody PqrMasterEntity p, @PathVariable int countryId){
        ResponseModel response=this.pqrMasterS.create(p,countryId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="update")
    public ResponseEntity update(@RequestBody PqrMasterEntity p){
        ResponseModel response=this.pqrMasterS.update(p); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{id}")
    public ResponseEntity delete(@PathVariable int id){
        ResponseModel response=this.pqrMasterS.delete(id); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findAll/{masterType}/{countryId}")
    public ResponseEntity findAll(@PathVariable String masterType, @PathVariable int countryId){
        ResponseModel response=this.pqrMasterS.findAll(masterType, countryId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findId/{masterType}/{name}")
    public ResponseEntity findId(@PathVariable String masterType,@PathVariable String name){
        ResponseModel response=this.pqrMasterS.findId(masterType,name); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list/{countryId}")
    public ResponseEntity list(@PathVariable int countryId){
        ResponseModel response=this.pqrMasterS.list(countryId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
