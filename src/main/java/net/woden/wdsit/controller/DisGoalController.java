package net.woden.wdsit.controller;

import net.woden.wdsit.entity.DisGoalEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.DisGoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("disGoal/")
public class DisGoalController {
 
    @Autowired
    private DisGoalService disGoalS; 

    @PostMapping(value="create")
    public ResponseEntity create(@RequestBody DisGoalEntity d){
        ResponseModel response=this.disGoalS.create(d); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
