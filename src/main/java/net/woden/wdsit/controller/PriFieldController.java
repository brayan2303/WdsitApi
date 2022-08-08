package net.woden.wdsit.controller;

import net.woden.wdsit.entity.PriFieldEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.PriFieldService;
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
@RequestMapping("priField/")
public class PriFieldController {
 
    @Autowired
    private PriFieldService priFieldS; 

    @PostMapping(value="create")
    public ResponseEntity create(@RequestBody PriFieldEntity p){
        ResponseModel response=this.priFieldS.create(p); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="update")
    public ResponseEntity update(@RequestBody PriFieldEntity p){
        ResponseModel response=this.priFieldS.update(p); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{fieldId}")
    public ResponseEntity delete(@PathVariable int fieldId){
        ResponseModel response=this.priFieldS.delete(fieldId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findByLabelId/{labelId}")
    public ResponseEntity findByLabelId(@PathVariable int labelId){
        ResponseModel response=this.priFieldS.findByLabelId(labelId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
     @GetMapping(value="findByLabelIdOrder/{labelId}")
    public ResponseEntity findByLabelIdOrder(@PathVariable int labelId){
        ResponseModel response=this.priFieldS.findByLabelIdOrder(labelId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list/{labelId}")
    public ResponseEntity list(@PathVariable int labelId){
        ResponseModel response=this.priFieldS.list(labelId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="listAutomatic/{labelId}")
    public ResponseEntity listAutomatic(@PathVariable int labelId){
        ResponseModel response=this.priFieldS.listAutomatic(labelId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
     @GetMapping(value="listAutomaticSmall/{labelId}")
    public ResponseEntity listAutomaticSmall(@PathVariable int labelId){
        ResponseModel response=this.priFieldS.listAutomaticSmall(labelId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
