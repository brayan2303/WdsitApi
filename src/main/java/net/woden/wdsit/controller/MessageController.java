/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.controller;

import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author b.algecira
 */
/*

@RestController
@RequestMapping("MessageS/")
public class MessageController {
    @Autowired
    private MessageService MessageS;
      @PostMapping("sendEmail/{namefiles}/{destinario}")
    public ResponseEntity sendEmail(@PathVariable String namefiles, @PathVariable int destinario){
        ResponseModel response=this.MessageS.sendEmail(namefiles,destinario);
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
*/