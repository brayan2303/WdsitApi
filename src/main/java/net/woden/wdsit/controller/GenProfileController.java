package net.woden.wdsit.controller;

import net.woden.wdsit.entity.GenProfileEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.GenProfileService;
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
@RequestMapping("genProfile/")
public class GenProfileController {
 
    @Autowired
    private GenProfileService genProfileS; 

    @PostMapping(value="create")
    public ResponseEntity create(@RequestBody GenProfileEntity g){
        ResponseModel response=this.genProfileS.create(g); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="update")
    public ResponseEntity update(@RequestBody GenProfileEntity g){
        ResponseModel response=this.genProfileS.update(g); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{profileId}")
    public ResponseEntity delete(@PathVariable int profileId){
        ResponseModel response=this.genProfileS.delete(profileId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findById/{profileId}")
    public ResponseEntity findById(@PathVariable int profileId){
        ResponseModel response=this.genProfileS.findById(profileId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findAll")
    public ResponseEntity findAll(){
        ResponseModel response=this.genProfileS.findAll(); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list")
    public ResponseEntity list(){
        ResponseModel response=this.genProfileS.list(); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
