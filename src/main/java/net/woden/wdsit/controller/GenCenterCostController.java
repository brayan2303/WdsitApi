package net.woden.wdsit.controller;

import net.woden.wdsit.entity.GenCenterCostEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.GenCenterCostService;
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
@RequestMapping("genCenterCost/")
public class GenCenterCostController {
 
    @Autowired
    private GenCenterCostService genCenterCostS; 

    @PostMapping(value="create")
    public ResponseEntity create(@RequestBody GenCenterCostEntity g){
        ResponseModel response=this.genCenterCostS.create(g); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="update")
    public ResponseEntity update(@RequestBody GenCenterCostEntity g){
        ResponseModel response=this.genCenterCostS.update(g); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{centerCostId}")
    public ResponseEntity delete(@PathVariable int centerCostId){
        ResponseModel response=this.genCenterCostS.delete(centerCostId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findAll")
    public ResponseEntity findAll(){
        ResponseModel response=this.genCenterCostS.findAll(); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findBySegmentId/{segmentId}")
    public ResponseEntity findBySegmentId(@PathVariable int segmentId){
        ResponseModel response=this.genCenterCostS.findBySegmentId(segmentId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findById/{centerCostId}")
    public ResponseEntity findById(@PathVariable int centerCostId){
        ResponseModel response=this.genCenterCostS.findById(centerCostId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list")
    public ResponseEntity list(){
        ResponseModel response=this.genCenterCostS.list(); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
