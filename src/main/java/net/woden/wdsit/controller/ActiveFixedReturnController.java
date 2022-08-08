/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.controller;

import net.woden.wdsit.entity.ActiveFixedReturnEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.ActiveFixedReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PutMapping;
/**
 *
 * @author b.algecira
 */
@RestController
@RequestMapping("ActiveFixedReturn/")
public class ActiveFixedReturnController {
    
     @Autowired
    private ActiveFixedReturnService ActiveFixedReturn;
     
     @PostMapping(value = "create") // servicio crear y almacenar
    public ResponseEntity create(@RequestBody ActiveFixedReturnEntity a) {
        ResponseModel response = this.ActiveFixedReturn.create(a);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
     @DeleteMapping(value = "delete/{id}") //servicio eliminar
    public ResponseEntity delete(@PathVariable int id) {
        ResponseModel response = this.ActiveFixedReturn.delete(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @GetMapping(value = "list") //servicio mostrar en pantalla
    public ResponseEntity list() {
        ResponseModel response = this.ActiveFixedReturn.list();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
     @PostMapping(value = "loadFile/{identification}/{creationDate}") // servicio carga de archivos al servidor
    public ResponseEntity loadFile(@PathVariable int identification, @PathVariable String creationDate, @RequestParam("files") MultipartFile[] files) {
        ResponseModel response = this.ActiveFixedReturn.loadFile(identification, creationDate, files);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
      @GetMapping(value = "listFile/{identification}/{creationDate}")
    public ResponseEntity listFile(@PathVariable int identification, @PathVariable String creationDate) {
        ResponseModel response = this.ActiveFixedReturn.listFile(identification, creationDate);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value = "updateSerial/{serial}") //servicio actualizar
    public ResponseEntity updateSerial(@PathVariable String serial) {
        ResponseModel response = this.ActiveFixedReturn.updateSerial(serial);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
