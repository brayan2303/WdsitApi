package net.woden.wdsit.controller;

import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.GenApplicationPersonProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("genApplicationPersonProfile/")
public class GenApplicationPersonProfileController {
 
    @Autowired
    private GenApplicationPersonProfileService genApplicationPersonProfileS; 

    @PostMapping(value="create/{applicationId}/{personId}/{profileId}")
    public ResponseEntity create(@PathVariable int applicationId,@PathVariable int personId,@PathVariable int profileId){
        ResponseModel response=this.genApplicationPersonProfileS.create(applicationId,personId,profileId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{applicationPersonProfileId}/{applicationId}/{personId}/{profileId}")
    public ResponseEntity delete(@PathVariable int applicationPersonProfileId,@PathVariable int applicationId,@PathVariable int personId,@PathVariable int profileId){
        ResponseModel response=this.genApplicationPersonProfileS.delete(applicationPersonProfileId,applicationId,personId,profileId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list/{applicationId}/{personId}")
    public ResponseEntity list(@PathVariable int applicationId,@PathVariable int personId){
        ResponseModel response=this.genApplicationPersonProfileS.list(applicationId,personId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="listProfile/{personId}/{applicationName}")
    public ResponseEntity listProfile(@PathVariable int personId,@PathVariable String applicationName){
        ResponseModel response=this.genApplicationPersonProfileS.listProfile(personId,applicationName); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
