package net.woden.wdsit.controller;

import net.woden.wdsit.entity.BscNotificationUserEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.BscNotificationUserService;
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

@RestController
@RequestMapping("bscNotificationUser/")
public class BscNotificationUserController {
 
    @Autowired
    private BscNotificationUserService bscNotificationUserS; 

    @PostMapping(value="create")
    public ResponseEntity create(@RequestBody BscNotificationUserEntity b){
        ResponseModel response=this.bscNotificationUserS.create(b); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{notificationId}/{userId}")
    public ResponseEntity delete(@PathVariable int notificationId,@PathVariable int userId){
        ResponseModel response=this.bscNotificationUserS.delete(notificationId,userId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list/{notificationName}")
    public ResponseEntity list(@PathVariable String notificationName){
        ResponseModel response=this.bscNotificationUserS.list(notificationName); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findAll/{notificationId}")
    public ResponseEntity findAll(@PathVariable int notificationId){
        ResponseModel response=this.bscNotificationUserS.findAll(notificationId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
