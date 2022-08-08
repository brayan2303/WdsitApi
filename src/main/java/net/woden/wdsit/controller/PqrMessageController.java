/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.controller;

import net.woden.wdsit.entity.PqrMessageEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.PqrMessageService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author b.algecira
 */
@RestController
@RequestMapping("PqrMessage/")
public class PqrMessageController {

    @Autowired
    private PqrMessageService PqrMessage;

    @PostMapping(value = "create")
    public ResponseEntity create(@RequestBody PqrMessageEntity p) {
        ResponseModel response = this.PqrMessage.create(p);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping(value = "delete/{id}")
    public ResponseEntity delete(@PathVariable int id) {
        ResponseModel response = this.PqrMessage.deletes(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "list/{userId}")
    public ResponseEntity list(@PathVariable int userId) {
        ResponseModel response = this.PqrMessage.list(userId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value = "loadFile/{number}/{creationDate}")
    public ResponseEntity loadFile(@PathVariable String number,@PathVariable String creationDate, @RequestParam("files") MultipartFile[] files) {
        ResponseModel response = this.PqrMessage.loadFile(number,creationDate,files);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "listFile/{number}/{creationDate}")
    public ResponseEntity listFile(@PathVariable String number,@PathVariable String creationDate) {
        ResponseModel response = this.PqrMessage.listFile(number,creationDate);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
     @PutMapping(value = "updateMessage/{number}")
    public ResponseEntity updateMessage(@PathVariable String number) {
        ResponseModel response = this.PqrMessage.updateMessage(number);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
     @PostMapping("sendEmail")
    public ResponseEntity sendEmail(@RequestParam String id,@RequestParam String number,@RequestParam int destinatarioId){
        ResponseModel response=this.PqrMessage.sendEmail(id,number,destinatarioId);
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
