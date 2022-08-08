package net.woden.wdsit.controller;

import net.woden.wdsit.entity.RepMailEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.RepMailService;
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
@RequestMapping("repMail/")
public class RepMailController {
 
    @Autowired
    private RepMailService repMailS; 

    @PostMapping(value="create")
    public ResponseEntity create(@RequestBody RepMailEntity r){
        ResponseModel response=this.repMailS.create(r); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="update")
    public ResponseEntity update(@RequestBody RepMailEntity r){
        ResponseModel response=this.repMailS.update(r); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{mailId}")
    public ResponseEntity delete(@PathVariable int mailId){
        ResponseModel response=this.repMailS.delete(mailId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findById/{mailId}")
    public ResponseEntity findById(@PathVariable int mailId){
        ResponseModel response=this.repMailS.findById(mailId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list")
    public ResponseEntity list(){
        ResponseModel response=this.repMailS.list(); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
