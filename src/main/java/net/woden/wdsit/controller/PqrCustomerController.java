/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.controller;

import java.util.ArrayList;
import net.woden.wdsit.entity.PqrCustomerEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.PqrCustomerService;
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
@RequestMapping("PqrCustomerS/")
public class PqrCustomerController {
    
    @Autowired
    private PqrCustomerService PqrCustomerS;

    @PostMapping(value = "create") // servicio crear y almacenar
    public ResponseEntity create(@RequestBody PqrCustomerEntity p) {
        ResponseModel response = this.PqrCustomerS.create(p);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PostMapping(value = "createTicket/{userId}") // servicio crear y almacenar
    public ResponseEntity createTicket(@PathVariable int userId) {
        ResponseModel response = this.PqrCustomerS.createTicket(userId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping(value = "update/{ticket}/{countryId}")
    public ResponseEntity update(@PathVariable String ticket, @RequestBody PqrCustomerEntity p, @PathVariable int countryId) {
        ResponseModel response = this.PqrCustomerS.update(ticket, p, countryId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping(value = "delete/{id}")
    public ResponseEntity delete(@PathVariable int id) {
        ResponseModel response = this.PqrCustomerS.delete(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "list/{userId}") //servicio mostrar en pantalla
    public ResponseEntity list(@PathVariable int userId) {
        ResponseModel response = this.PqrCustomerS.list(userId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
     @GetMapping("findById/{id}")
    public ResponseEntity findById(@PathVariable String id){
        ResponseModel response=this.PqrCustomerS.findById(id);
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @PostMapping(value = "loadFile/{ticket}/{creationDate}") // servicio carga de archivos al servidor
    public ResponseEntity loadFile(@PathVariable String ticket, @PathVariable String creationDate, @RequestParam("files") MultipartFile[] files) {
        ResponseModel response = this.PqrCustomerS.loadFile(ticket, creationDate, files);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
      @GetMapping(value = "listFile/{ticket}/{creationDate}")
    public ResponseEntity listFile(@PathVariable String ticket, @PathVariable String creationDate) {
        ResponseModel response = this.PqrCustomerS.listFile(ticket, creationDate);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
        @PostMapping("allDescription")
    public ResponseEntity allDescription(@RequestParam String ticketId, @RequestParam int userId){
        ResponseModel response=this.PqrCustomerS.allDescription(ticketId,userId);
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
     @GetMapping("listAll/{id}/{userId}")
    public ResponseEntity listAll(@PathVariable String id, @PathVariable int userId){
        ResponseModel response=this.PqrCustomerS.listAll(id, userId);
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
        @PostMapping("sendEmail")
    public ResponseEntity sendEmail(@RequestParam String id,@RequestParam int destinatarioId){
        ResponseModel response=this.PqrCustomerS.sendEmail(id,destinatarioId);
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PostMapping("emailLog")
    public ResponseEntity emailLog(@RequestParam String userId,@RequestParam int destinatarioId,@RequestParam String status,@RequestParam String typeStatus){
        ResponseModel response=this.PqrCustomerS.emailLog(userId,destinatarioId,status,typeStatus);
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
