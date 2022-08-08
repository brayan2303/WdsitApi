/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.woden.wdsit.controller;

import java.io.IOException;
import net.woden.wdsit.entity.ScpAuditSerialEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.ScpAuditSerialService;
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
@RequestMapping("ScpAuditSerialS/")
public class ScpAuditSerialController {
    
    @Autowired
    private ScpAuditSerialService ScpAuditSerialS;

    @PostMapping(value = "create") // servicio crear y almacenar
    public ResponseEntity create(@RequestBody ScpAuditSerialEntity s) {
        ResponseModel response = this.ScpAuditSerialS.create(s);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @DeleteMapping(value = "delete/{id}") //servicio eliminar
    public ResponseEntity delete(@PathVariable int id) {
        ResponseModel response = this.ScpAuditSerialS.delete(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "list") //servicio mostrar en pantalla
    public ResponseEntity list() {
        ResponseModel response = this.ScpAuditSerialS.list();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @GetMapping(value = "listAudit") //servicio mostrar en pantalla
    public ResponseEntity listAudit() {
        ResponseModel response = this.ScpAuditSerialS.listAudit();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
     @GetMapping(value = "listMotif") //servicio mostrar en pantalla
    public ResponseEntity listMotif() {
        ResponseModel response = this.ScpAuditSerialS.listMotif();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
     @GetMapping(value = "findById/{levelRuleQuantityId}") //servicio mostrar en pantalla
    public ResponseEntity findById(@PathVariable int levelRuleQuantityId) {
        ResponseModel response = this.ScpAuditSerialS.findById(levelRuleQuantityId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
     @GetMapping(value = "listSearch/{serial}") //servicio mostrar en pantalla
    public ResponseEntity listSearch(@PathVariable String serial) {
        ResponseModel response = this.ScpAuditSerialS.listSearch(serial);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
     @GetMapping(value = "listLevel/{levelId}") //servicio mostrar en pantalla
    public ResponseEntity listLevel(@PathVariable int levelId) {
        ResponseModel response = this.ScpAuditSerialS.listLevel(levelId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    
     @GetMapping(value = "listAuditSerial/{id}") //servicio mostrar en pantalla
    public ResponseEntity listAuditSerial(@PathVariable int id) {
        ResponseModel response = this.ScpAuditSerialS.listAuditSerial(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
     @PostMapping(value = "loadFile/{id}/{serial}") // servicio carga de archivos al servidor
    public ResponseEntity loadFile(@PathVariable int id,@PathVariable String serial, @RequestParam("files") MultipartFile[] files) throws IOException {
        ResponseModel response = this.ScpAuditSerialS.loadFile(id,serial, files);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
     @PutMapping(value="updateClose/{id}")
    public ResponseEntity updateClose(@PathVariable int id){
        ResponseModel response=this.ScpAuditSerialS.updateClose(id); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
