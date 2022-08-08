/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.controller;

import net.woden.wdsit.entity.ProNotificationUserEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.ProNotificationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author b.algecira
 */
@RestController
@RequestMapping("proNotificationUser/")
public class ProNotificationUserController {
    
    @Autowired
    private ProNotificationUserService proNotificationUserS; 

    @PostMapping(value="create")
    public ResponseEntity create(@RequestBody ProNotificationUserEntity b){
        ResponseModel response=this.proNotificationUserS.create(b); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{notificationId}/{userId}")
    public ResponseEntity delete(@PathVariable int notificationId,@PathVariable int userId){
        ResponseModel response=this.proNotificationUserS.delete(notificationId,userId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list/{notificationName}")
    public ResponseEntity list(@PathVariable String notificationName){
        ResponseModel response=this.proNotificationUserS.list(notificationName); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findAll/{notificationId}")
    public ResponseEntity findAll(@PathVariable int notificationId){
        ResponseModel response=this.proNotificationUserS.findAll(notificationId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
