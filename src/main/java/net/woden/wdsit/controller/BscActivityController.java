package net.woden.wdsit.controller;

import net.woden.wdsit.entity.BscActivityEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.BscActivityService;
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
@RequestMapping("bscActivity/")
public class BscActivityController {
 
    @Autowired
    private BscActivityService bscActivityS; 

    @PostMapping(value="create")
    public ResponseEntity create(@RequestBody BscActivityEntity b){
        ResponseModel response=this.bscActivityS.create(b); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="update")
    public ResponseEntity update(@RequestBody BscActivityEntity b){
        ResponseModel response=this.bscActivityS.update(b); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{activityId}")
    public ResponseEntity delete(@PathVariable int activityId){
        ResponseModel response=this.bscActivityS.delete(activityId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="openClose/{activityId}/{status}")
    public ResponseEntity openClose(@PathVariable int activityId,@PathVariable String status){
        ResponseModel response=this.bscActivityS.openClose(activityId,status); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list/{workPlanId}")
    public ResponseEntity list(@PathVariable int workPlanId){
        ResponseModel response=this.bscActivityS.list(workPlanId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
