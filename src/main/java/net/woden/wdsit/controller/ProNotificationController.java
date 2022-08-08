/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.controller;

import java.util.ArrayList;
import net.woden.wdsit.entity.ProNotificationEntity;
import net.woden.wdsit.entity.ProNotificationUserEntity;
import net.woden.wdsit.model.ProNotificationModel;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.ProNotificationService;
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

/**
 *
 * @author b.algecira
 */
@RestController
@RequestMapping("proNotification/")
public class ProNotificationController {
    
    @Autowired
    private ProNotificationService proNotificationS; 

    @PostMapping(value="create")
    public ResponseEntity create(@RequestBody ProNotificationEntity b){
        ResponseModel response=this.proNotificationS.create(b); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="update")
    public ResponseEntity update(@RequestBody ProNotificationEntity b){
        ResponseModel response=this.proNotificationS.update(b); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{notificationId}")
    public ResponseEntity delete(@PathVariable int notificationId){
        ResponseModel response=this.proNotificationS.delete(notificationId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findByName/{name}")
    public ResponseEntity findByName(@PathVariable String name){
        ResponseModel response=this.proNotificationS.findByName(name); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list")
    public ResponseEntity list(){
        ResponseModel response=this.proNotificationS.list(); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="listMail/{notificationName}/{perspectiveId}/{measurementId}/{actionPlanId}")
    public ResponseEntity listMail(@PathVariable String notificationName,@PathVariable int perspectiveId,@PathVariable int measurementId,@PathVariable int actionPlanId){
        ResponseModel response=this.proNotificationS.listMail(notificationName,perspectiveId,measurementId,actionPlanId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PostMapping(value="send/{name}")
    public ResponseEntity send(@RequestBody ProNotificationModel body,@PathVariable String name){
        ResponseModel response=this.proNotificationS.send(name,(ArrayList<ProNotificationUserEntity>)body.getMails(),(ArrayList<String>)body.getVariables()); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
