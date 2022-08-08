package net.woden.wdsit.controller;

import net.woden.wdsit.entity.BscActionPlanEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.BscActionPlanService;
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
@RequestMapping("bscActionPlan/")
public class BscActionPlanController {
 
    @Autowired
    private BscActionPlanService bscActionPlanS; 

    @PostMapping(value="create")
    public ResponseEntity create(@RequestBody BscActionPlanEntity b){
        ResponseModel response=this.bscActionPlanS.create(b); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="update")
    public ResponseEntity update(@RequestBody BscActionPlanEntity b){
        ResponseModel response=this.bscActionPlanS.update(b); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{actionPlanId}")
    public ResponseEntity delete(@PathVariable int actionPlanId){
        ResponseModel response=this.bscActionPlanS.delete(actionPlanId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="openClose/{actionPlanId}/{openClose}")
    public ResponseEntity openClose(@PathVariable int actionPlanId,@PathVariable boolean openClose){
        ResponseModel response=this.bscActionPlanS.openClose(actionPlanId,openClose); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="approveReject/{actionPlanId}/{status}")
    public ResponseEntity approveReject(@PathVariable int actionPlanId,@PathVariable String status){
        ResponseModel response=this.bscActionPlanS.approveReject(actionPlanId,status); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findById/{actionPlanId}")
    public ResponseEntity findById(@PathVariable int actionPlanId){
        ResponseModel response=this.bscActionPlanS.findById(actionPlanId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list/{analysisId}")
    public ResponseEntity list(@PathVariable int analysisId){
        ResponseModel response=this.bscActionPlanS.list(analysisId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
