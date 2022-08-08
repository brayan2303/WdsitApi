package net.woden.wdsit.controller;

import java.util.ArrayList;
import net.woden.wdsit.entity.BscNotificationEntity;
import net.woden.wdsit.entity.BscNotificationUserEntity;
import net.woden.wdsit.model.BscNotificationModel;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.BscNotificationService;
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
@RequestMapping("bscNotification/")
public class BscNotificationController {
 
    @Autowired
    private BscNotificationService bscNotificationS; 

    @PostMapping(value="create")
    public ResponseEntity create(@RequestBody BscNotificationEntity b){
        ResponseModel response=this.bscNotificationS.create(b); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="update")
    public ResponseEntity update(@RequestBody BscNotificationEntity b){
        ResponseModel response=this.bscNotificationS.update(b); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{notificationId}")
    public ResponseEntity delete(@PathVariable int notificationId){
        ResponseModel response=this.bscNotificationS.delete(notificationId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findByName/{name}")
    public ResponseEntity findByName(@PathVariable String name){
        ResponseModel response=this.bscNotificationS.findByName(name); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list")
    public ResponseEntity list(){
        ResponseModel response=this.bscNotificationS.list(); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="listMail/{notificationName}/{perspectiveId}/{measurementId}/{actionPlanId}")
    public ResponseEntity listMail(@PathVariable String notificationName,@PathVariable int perspectiveId,@PathVariable int measurementId,@PathVariable int actionPlanId){
        ResponseModel response=this.bscNotificationS.listMail(notificationName,perspectiveId,measurementId,actionPlanId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PostMapping(value="send/{name}")
    public ResponseEntity send(@RequestBody BscNotificationModel body,@PathVariable String name){
        ResponseModel response=this.bscNotificationS.send(name,(ArrayList<BscNotificationUserEntity>)body.getMails(),(ArrayList<String>)body.getVariables()); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

