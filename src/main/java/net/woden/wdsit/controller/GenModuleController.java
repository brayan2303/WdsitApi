package net.woden.wdsit.controller;

import net.woden.wdsit.entity.GenModuleEntity;
import net.woden.wdsit.entity.GenProfileModuleEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.GenModuleService;
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
@RequestMapping("genModule/")
public class GenModuleController {
 
    @Autowired
    private GenModuleService genModuleS; 

    @PostMapping(value="create")
    public ResponseEntity create(@RequestBody GenModuleEntity g){
        ResponseModel response=this.genModuleS.create(g); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="update")
    public ResponseEntity update(@RequestBody GenModuleEntity g){
        ResponseModel response=this.genModuleS.update(g); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{moduleId}")
    public ResponseEntity delete(@PathVariable int moduleId){
        ResponseModel response=this.genModuleS.delete(moduleId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PostMapping(value="add")
    public ResponseEntity add(@RequestBody GenProfileModuleEntity g){
        ResponseModel response=this.genModuleS.add(g); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="remove/{profileId}/{moduleId}")
    public ResponseEntity remove(@PathVariable int profileId,@PathVariable int moduleId){
        ResponseModel response=this.genModuleS.remove(profileId,moduleId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findAll/{sectionId}/{profileId}")
    public ResponseEntity findAll(@PathVariable int profileId,@PathVariable int sectionId){
        ResponseModel response=this.genModuleS.findAll(sectionId,profileId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findById/{moduleId}")
    public ResponseEntity findById(@PathVariable int moduleId){
        ResponseModel response=this.genModuleS.findById(moduleId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findByProfileId/{profileId}/{applicationName}")
    public ResponseEntity findByProfileId(@PathVariable int profileId,@PathVariable String applicationName){
        ResponseModel response=this.genModuleS.findByProfileId(profileId,applicationName); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list")
    public ResponseEntity list(){
        ResponseModel response=this.genModuleS.list(); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
