package net.woden.wdsit.controller;

import java.util.ArrayList;
import net.woden.wdsit.entity.PriLogEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.PriLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("priLog/")
public class PriLogController {
 
    @Autowired
    private PriLogService priLogS; 

    @PostMapping(value="create")
    public ResponseEntity create(@RequestBody ArrayList<PriLogEntity>array){
        ResponseModel response=this.priLogS.create(array); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="printDay")
    public ResponseEntity printDay(){
        ResponseModel response=this.priLogS.printDay(); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="printLabel")
    public ResponseEntity printLabel(){
        ResponseModel response=this.priLogS.printLabel(); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
