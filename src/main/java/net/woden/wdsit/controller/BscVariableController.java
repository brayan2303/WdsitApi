package net.woden.wdsit.controller;

import net.woden.wdsit.entity.BscVariableEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.BscVariableService;
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
@RequestMapping("bscVariable/")
public class BscVariableController {
 
    @Autowired
    private BscVariableService bscVariableS; 

    @PostMapping(value="create")
    public ResponseEntity create(@RequestBody BscVariableEntity b){
        ResponseModel response=this.bscVariableS.create(b); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="update")
    public ResponseEntity update(@RequestBody BscVariableEntity b){
        ResponseModel response=this.bscVariableS.update(b); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{variableId}")
    public ResponseEntity delete(@PathVariable int variableId){
        ResponseModel response=this.bscVariableS.delete(variableId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list")
    public ResponseEntity list(){
        ResponseModel response=this.bscVariableS.list(); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="listActive")
    public ResponseEntity listActive(){
        ResponseModel response=this.bscVariableS.listActive(); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
