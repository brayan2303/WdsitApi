package net.woden.wdsit.controller;

import java.util.ArrayList;
import net.woden.wdsit.entity.PqrMailEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.PqrMailService;
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
@RequestMapping("pqrMail/")
public class PqrMailController {
 
    @Autowired
    private PqrMailService pqrMailS; 

    @PostMapping(value="create")
    public ResponseEntity create(@RequestBody PqrMailEntity p){
        ResponseModel response=this.pqrMailS.create(p); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="update")
    public ResponseEntity update(@RequestBody PqrMailEntity p){
        ResponseModel response=this.pqrMailS.update(p); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{mailId}")
    public ResponseEntity delete(@PathVariable int mailId){
        ResponseModel response=this.pqrMailS.delete(mailId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list")
    public ResponseEntity list(){
        ResponseModel response=this.pqrMailS.list(); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PostMapping(value="send/{id}")
    public ResponseEntity send(@PathVariable int id,@RequestBody ArrayList<String>variables){
        ResponseModel response=this.pqrMailS.send(id,variables); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
