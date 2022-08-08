package net.woden.wdsit.controller;

import net.woden.wdsit.entity.BscActionEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.BscActionService;
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
@RequestMapping("bscAction/")
public class BscActionController {
 
    @Autowired
    private BscActionService bscActionS; 

    @PostMapping(value="create")
    public ResponseEntity create(@RequestBody BscActionEntity b){
        ResponseModel response=this.bscActionS.create(b); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="update")
    public ResponseEntity update(@RequestBody BscActionEntity b){
        ResponseModel response=this.bscActionS.update(b); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="openClose/{actionId}/{openClose}")
    public ResponseEntity openClose(@PathVariable int actionId,@PathVariable boolean openClose){
        ResponseModel response=this.bscActionS.openClose(actionId,openClose); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{actionId}")
    public ResponseEntity delete(@PathVariable int actionId){
        ResponseModel response=this.bscActionS.delete(actionId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findById/{actionId}")
    public ResponseEntity findById(@PathVariable int actionId){
        ResponseModel response=this.bscActionS.findById(actionId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findOpen/{actionPlanId}")
    public ResponseEntity findOpen(@PathVariable int actionPlanId){
        ResponseModel response=this.bscActionS.findOpen(actionPlanId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list/{actionPlanId}")
    public ResponseEntity list(@PathVariable int actionPlanId){
        ResponseModel response=this.bscActionS.list(actionPlanId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
