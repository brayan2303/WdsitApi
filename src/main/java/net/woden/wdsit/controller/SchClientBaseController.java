/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.controller;

import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.SchClientBaseService;
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
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author m.pulido
 */
@RestController
@RequestMapping("SchClientBase/")
public class SchClientBaseController {
    
    @Autowired
    private SchClientBaseService SchClientBaseS;
    

    @PostMapping("create/{customerId}")
    public ResponseEntity create(@PathVariable int customerId, @RequestBody MultipartFile file) {
        ResponseModel response = this.SchClientBaseS.create(customerId, file);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @PostMapping("createHistory/{customerId}/{userId}")
    public ResponseEntity createHistory(@PathVariable int customerId, @PathVariable int userId) {
        ResponseModel response = this.SchClientBaseS.createHistory(customerId, userId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @GetMapping(value = "list/{customerId}") //servicio mostrar en pantalla
    public ResponseEntity list(@PathVariable int customerId){
        ResponseModel response = this.SchClientBaseS.list(customerId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @DeleteMapping(value = "delete/{customerId}") //servicio mostrar en pantalla
    public ResponseEntity delete(@PathVariable int customerId){
        ResponseModel response = this.SchClientBaseS.delete(customerId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @GetMapping(value = "getByIdentification/{identification}") //servicio mostrar en pantalla
    public ResponseEntity getByIdentification(@PathVariable int identification){
        ResponseModel response = this.SchClientBaseS.searchByIdentification(identification);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
