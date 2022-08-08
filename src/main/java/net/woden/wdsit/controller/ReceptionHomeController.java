/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.woden.wdsit.controller;

import net.woden.wdsit.entity.ReceptionHomeEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.ReceptionHomeService;
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
@RequestMapping("ReceptionHomeS/")
public class ReceptionHomeController {

    @Autowired
    private ReceptionHomeService ReceptionHomeS;

    @PostMapping(value = "create/{userId}") // servicio crear y almacenar
    public ResponseEntity create(@RequestBody ReceptionHomeEntity r, @PathVariable int userId) {
        ResponseModel response = this.ReceptionHomeS.create(r, userId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value = "createExit/{userId}") // servicio crear y almacenar
    public ResponseEntity createExit(@RequestBody ReceptionHomeEntity r, @PathVariable int userId) {
        ResponseModel response = this.ReceptionHomeS.createExit(r, userId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping(value = "update") //servicio actualizar
    public ResponseEntity update(@RequestBody ReceptionHomeEntity r, int userIdUpdate) {
        ResponseModel response = this.ReceptionHomeS.update(r, userIdUpdate);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping(value = "delete/{id}") //servicio eliminar
    public ResponseEntity delete(@PathVariable int id) {
        ResponseModel response = this.ReceptionHomeS.delete(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "list") //servicio mostrar en pantalla
    public ResponseEntity list() {
        ResponseModel response = this.ReceptionHomeS.list();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "findById/{id}")
    public ResponseEntity findById(@PathVariable int id) {
        ResponseModel response = this.ReceptionHomeS.findById(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "findByIdentification/{identification}")
    public ResponseEntity findByIdentification(@PathVariable String identification) {
        ResponseModel response = this.ReceptionHomeS.findByIdentification(identification);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "findByIdentificationPerson/{identification}")
    public ResponseEntity findByIdentificationPerson(@PathVariable String identification) {
        ResponseModel response = this.ReceptionHomeS.findByIdentificationPerson(identification);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "findByIdentificationTicket/{identification}")
    public ResponseEntity findByIdentificationTicket(@PathVariable String identification) {
        ResponseModel response = this.ReceptionHomeS.findByIdentificationTicket(identification);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value = "loadFile/{identification}") // servicio carga de archivos al servidor
    public ResponseEntity loadFile(@PathVariable int identification, @RequestParam("files") MultipartFile[] files) {
        ResponseModel response = this.ReceptionHomeS.loadFile(identification, files);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "findType/{typeId}")
    public ResponseEntity findType(@PathVariable int typeId) {
        ResponseModel response = this.ReceptionHomeS.findType(typeId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
