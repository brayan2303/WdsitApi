/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.controller;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import java.io.IOException;
import net.woden.wdsit.entity.CertCertEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.CertCertService;
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
 * @author m.pulido
 */
@RestController
@RequestMapping("certCert/")
public class CertCertController {
    
    @Autowired
    private CertCertService CertCert;

    @PostMapping(value = "create") // servicio crear y almacenar
    public ResponseEntity create(@RequestBody CertCertEntity a) {
        ResponseModel response = this.CertCert.create(a);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping(value = "update") //servicio actualizar
    public ResponseEntity update(@RequestBody CertCertEntity a) {
        ResponseModel response = this.CertCert.update(a);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping(value = "delete/{id}") //servicio eliminar
    public ResponseEntity delete(@PathVariable int id) {
        ResponseModel response = this.CertCert.delete(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "list") //servicio mostrar en pantalla
    public ResponseEntity list() {
        ResponseModel response = this.CertCert.list();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "findById/{id}")
    public ResponseEntity findById(@PathVariable int id) {
        ResponseModel response = this.CertCert.findById(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @GetMapping(value = "findAll/{personId}")
    public ResponseEntity findAll(@PathVariable int personId) {
        ResponseModel response = this.CertCert.findAll(personId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @GetMapping(value = "findAllByPersonId/{personId}")
    public ResponseEntity findAllByPersonId(@PathVariable int personId) {
        ResponseModel response = this.CertCert.findAllByPersonId(personId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @GetMapping(value = "ftpTIGO")
    public ResponseEntity ftpTIGO() throws JSchException, JSchException, IllegalAccessException, IllegalAccessException, SftpException, IOException {
        ResponseModel response = this.CertCert.FTPTIGO();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @GetMapping(value="listFile/{yearId}/{certId}/{periodicityId}/{monthId}/{userId}")
    public ResponseEntity listFile(@PathVariable int yearId, @PathVariable int certId, @PathVariable int periodicityId, @PathVariable int monthId, @PathVariable int userId){
        ResponseModel response=this.CertCert.getCert(yearId, certId, periodicityId, monthId, userId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
