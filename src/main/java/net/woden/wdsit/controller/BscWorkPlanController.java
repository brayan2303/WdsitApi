package net.woden.wdsit.controller;

import net.woden.wdsit.entity.BscWorkPlanEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.BscWorkPlanService;
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
@RequestMapping("bscWorkPlan/")
public class BscWorkPlanController {
 
    @Autowired
    private BscWorkPlanService bscWorkPlanS; 

    @PostMapping(value="create")
    public ResponseEntity create(@RequestBody BscWorkPlanEntity b){
        ResponseModel response=this.bscWorkPlanS.create(b); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="update")
    public ResponseEntity update(@RequestBody BscWorkPlanEntity b){
        ResponseModel response=this.bscWorkPlanS.update(b); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{workPlanId}")
    public ResponseEntity delete(@PathVariable int workPlanId){
        ResponseModel response=this.bscWorkPlanS.delete(workPlanId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list/{strategicObjetiveId}")
    public ResponseEntity list(@PathVariable int strategicObjetiveId){
        ResponseModel response=this.bscWorkPlanS.list(strategicObjetiveId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="listActive/{strategicObjetiveId}")
    public ResponseEntity listActive(@PathVariable int strategicObjetiveId){
        ResponseModel response=this.bscWorkPlanS.listActive(strategicObjetiveId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="total/{strategicObjetiveId}")
    public ResponseEntity total(@PathVariable int strategicObjetiveId){
        ResponseModel response=this.bscWorkPlanS.total(strategicObjetiveId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="percentage/{strategicObjetiveId}")
    public ResponseEntity percentage(@PathVariable int strategicObjetiveId){
        ResponseModel response=this.bscWorkPlanS.percentage(strategicObjetiveId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="percentageAdvances/{workPlanId}")
    public ResponseEntity percentageAdvances(@PathVariable int workPlanId){
        ResponseModel response=this.bscWorkPlanS.percentageAdvances(workPlanId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
