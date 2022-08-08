package net.woden.wdsit.controller;

import net.woden.wdsit.entity.DaiGoalEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.DaiGoalService;
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
@RequestMapping("daiGoal/")
public class DaiGoalController {
 
    @Autowired
    private DaiGoalService daiGoalS; 

    @PostMapping(value="create")
    public ResponseEntity create(@RequestBody DaiGoalEntity d){
        ResponseModel response=this.daiGoalS.create(d); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="update")
    public ResponseEntity update(@RequestBody DaiGoalEntity d){
        ResponseModel response=this.daiGoalS.update(d); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{id}")
    public ResponseEntity delete(@PathVariable int id){
        ResponseModel response=this.daiGoalS.delete(id); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list/{year}")
    public ResponseEntity list(@PathVariable int year){
        ResponseModel response=this.daiGoalS.list(year); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="dailyOperation/{country}")
    public ResponseEntity dailyOperation(@PathVariable String country){
        ResponseModel response=this.daiGoalS.dailyOperation(country); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="entry/{country}")
    public ResponseEntity entry(@PathVariable String country){
        ResponseModel response=this.daiGoalS.entry(country); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="dispatch/{country}")
    public ResponseEntity dispatch(@PathVariable String country){
        ResponseModel response=this.daiGoalS.dispatch(country); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="repair/{country}")
    public ResponseEntity repair(@PathVariable String country){
        ResponseModel response=this.daiGoalS.repair(country); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
