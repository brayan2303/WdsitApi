/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.controller;

import net.woden.wdsit.entity.PqrsClientSerialEntity;
import net.woden.wdsit.model.PqrsClientSerialSModel;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.PqrsClientSerialService;
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
@RequestMapping("PqrsClientSerialS/")
public class PqrsClientSerialController {
    
       @Autowired
    private PqrsClientSerialService PqrsClientSerialS;
    
    @PostMapping(value = "create/{countryId}") // servicio crear y almacenar
    public ResponseEntity create(@RequestBody PqrsClientSerialEntity p, @PathVariable int countryId) {
        ResponseModel response = this.PqrsClientSerialS.create(p, countryId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @PutMapping(value = "update") //servicio actualizar
    public ResponseEntity update(@RequestBody PqrsClientSerialEntity p) {
        ResponseModel response = this.PqrsClientSerialS.update(p);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping(value = "delete/{id}") //servicio eliminar
    public ResponseEntity delete(@PathVariable int id) {
        ResponseModel response = this.PqrsClientSerialS.delete(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value = "list") //servicio mostrar en pantalla
    public ResponseEntity list(@RequestParam String ticket, @RequestParam int creationPersonId) {
        ResponseModel response = this.PqrsClientSerialS.list(ticket,creationPersonId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "findById/{id}")
    public ResponseEntity findById(@PathVariable int id) {
        ResponseModel response = this.PqrsClientSerialS.findById(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
      @PostMapping(value="loadFile/{pqrsNumber}/{type}")
    public ResponseEntity loadFile(@PathVariable String pqrsNumber,@PathVariable String type,@RequestParam("files") MultipartFile[] files){
        ResponseModel response=this.PqrsClientSerialS.loadFile(pqrsNumber,type,files); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
   @GetMapping(value="listFile/{pqrsNumber}/{type}")
    public ResponseEntity listFile(@PathVariable String pqrsNumber,@PathVariable String type){
        ResponseModel response=this.PqrsClientSerialS.listFile(pqrsNumber,type); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
