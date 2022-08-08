package net.woden.wdsit.controller;

import net.woden.wdsit.entity.PqrMailStatusEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.PqrMailStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pqrMailStatus/")
public class PqrMailStatusController {
 
    @Autowired
    private PqrMailStatusService pqrMailStatusS; 

    @PostMapping(value="create")
    public ResponseEntity create(@RequestBody PqrMailStatusEntity p){
        ResponseModel response=this.pqrMailStatusS.create(p); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{mailId}/{personId}")
    public ResponseEntity delete(@PathVariable int mailId,@PathVariable int personId){
        ResponseModel response=this.pqrMailStatusS.delete(mailId,personId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findAll/{mailId}")
    public ResponseEntity findAll(@PathVariable int mailId){
        ResponseModel response=this.pqrMailStatusS.findAll(mailId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
