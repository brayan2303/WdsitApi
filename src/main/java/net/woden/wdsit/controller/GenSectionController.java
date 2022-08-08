package net.woden.wdsit.controller;

import net.woden.wdsit.entity.GenProfileSectionEntity;
import net.woden.wdsit.entity.GenSectionEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.GenSectionService;
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
@RequestMapping("genSection/")
public class GenSectionController {
 
    @Autowired
    private GenSectionService genSectionS; 

    @PostMapping(value="create")
    public ResponseEntity create(@RequestBody GenSectionEntity g){
        ResponseModel response=this.genSectionS.create(g); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="update")
    public ResponseEntity update(@RequestBody GenSectionEntity g){
        ResponseModel response=this.genSectionS.update(g); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{sectionId}")
    public ResponseEntity delete(@PathVariable int sectionId){
        ResponseModel response=this.genSectionS.delete(sectionId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PostMapping(value="add")
    public ResponseEntity add(@RequestBody GenProfileSectionEntity g){
        ResponseModel response=this.genSectionS.add(g); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="remove/{profileId}/{sectionId}")
    public ResponseEntity remove(@PathVariable int profileId,@PathVariable int sectionId){
        ResponseModel response=this.genSectionS.remove(profileId,sectionId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findAll/{applicationName}/{profileId}")
    public ResponseEntity findAll(@PathVariable String applicationName,@PathVariable int profileId){
        ResponseModel response=this.genSectionS.findAll(applicationName,profileId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findById/{sectionId}")
    public ResponseEntity findById(@PathVariable int sectionId){
        ResponseModel response=this.genSectionS.findById(sectionId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findByProfileId/{profileId}")
    public ResponseEntity findByProfileId(@PathVariable int profileId){
        ResponseModel response=this.genSectionS.findByProfileId(profileId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list/{applicationName}")
    public ResponseEntity list(@PathVariable String applicationName){
        ResponseModel response=this.genSectionS.list(applicationName); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="listAll")
    public ResponseEntity listAll(){
        ResponseModel response=this.genSectionS.listAll(); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
