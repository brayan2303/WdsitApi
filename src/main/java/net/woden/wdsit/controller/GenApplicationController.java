package net.woden.wdsit.controller;

import net.woden.wdsit.entity.GenApplicationEntity;
import net.woden.wdsit.entity.GenApplicationPersonEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.GenApplicationService;
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
@RequestMapping("genApplication/")
public class GenApplicationController {
 
    @Autowired
    private GenApplicationService genApplicationS; 

    @PostMapping(value="create")
    public ResponseEntity create(@RequestBody GenApplicationEntity g){
        ResponseModel response=this.genApplicationS.create(g); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="update")
    public ResponseEntity update(@RequestBody GenApplicationEntity g){
        ResponseModel response=this.genApplicationS.update(g); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{applicationId}")
    public ResponseEntity delete(@PathVariable int applicationId){
        ResponseModel response=this.genApplicationS.delete(applicationId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PostMapping(value="add")
    public ResponseEntity add(@RequestBody GenApplicationPersonEntity g){
        ResponseModel response=this.genApplicationS.add(g); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="remove/{personId}/{applicationId}")
    public ResponseEntity remove(@PathVariable int personId,@PathVariable int applicationId){
        ResponseModel response=this.genApplicationS.remove(personId,applicationId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findAll/{personId}")
    public ResponseEntity findAll(@PathVariable int personId){
        ResponseModel response=this.genApplicationS.findAll(personId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findByPersonId/{personId}")
    public ResponseEntity findByPersonId(@PathVariable int personId){
        ResponseModel response=this.genApplicationS.findByPersonId(personId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findById/{applicationId}")
    public ResponseEntity findById(@PathVariable int applicationId){
        ResponseModel response=this.genApplicationS.findById(applicationId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list")
    public ResponseEntity list(){
        ResponseModel response=this.genApplicationS.list(); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="listAll")
    public ResponseEntity listAll(){
        ResponseModel response=this.genApplicationS.listAll(); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
