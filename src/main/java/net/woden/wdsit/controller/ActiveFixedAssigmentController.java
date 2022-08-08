/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.controller;

import net.woden.wdsit.entity.ActiveFixedAssigmentEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.ActiveFixedAssigmentService;
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
@RequestMapping("ActiveFixedAssigment/")
public class ActiveFixedAssigmentController {

    @Autowired
    private ActiveFixedAssigmentService ActiveFixedAssigment;

    @PostMapping(value = "create") // servicio crear y almacenar
    public ResponseEntity create(@RequestBody ActiveFixedAssigmentEntity a) {
        ResponseModel response = this.ActiveFixedAssigment.create(a);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping(value = "update/{updateUser}") //servicio actualizar
    public ResponseEntity update(@RequestBody ActiveFixedAssigmentEntity a,@PathVariable int updateUser) {
        ResponseModel response = this.ActiveFixedAssigment.update(a,updateUser);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping(value = "delete/{id}") //servicio eliminar
    public ResponseEntity delete(@PathVariable int id) {
        ResponseModel response = this.ActiveFixedAssigment.delete(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "list") //servicio mostrar en pantalla
    public ResponseEntity list() {
        ResponseModel response = this.ActiveFixedAssigment.list();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "findById/{identification}") //servicio mostrar en pantalla
    public ResponseEntity findById(@PathVariable int identification) {
        ResponseModel response = this.ActiveFixedAssigment.findById(identification);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value = "loadFile/{identification}/{creationDate}") // servicio carga de archivos al servidor
    public ResponseEntity loadFile(@PathVariable int identification, @PathVariable String creationDate, @RequestParam("files") MultipartFile[] files) {
        ResponseModel response = this.ActiveFixedAssigment.loadFile(identification, creationDate, files);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "listFeatur/{productId}")
    public ResponseEntity listFeatur(@PathVariable int productId) {
        ResponseModel response = this.ActiveFixedAssigment.listFeatur(productId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "listFile/{identification}/{creationDate}")
    public ResponseEntity listFile(@PathVariable int identification, @PathVariable String creationDate) {
        ResponseModel response = this.ActiveFixedAssigment.listFile(identification, creationDate);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "assigFeaturAll/{id}")
    public ResponseEntity assigFeaturAll(@PathVariable int id) {
        ResponseModel response = this.ActiveFixedAssigment.assigFeaturAll(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "listPerson") //servicio mostrar en pantalla
    public ResponseEntity listPerson() {
        ResponseModel response = this.ActiveFixedAssigment.listPerson();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "listAnswer/{personRes}") //servicio mostrar en pantalla
    public ResponseEntity listAnswer(@PathVariable String personRes) {
        ResponseModel response = this.ActiveFixedAssigment.listAnswer(personRes);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
     @GetMapping(value = "listAnswerAll") //servicio mostrar en pantalla
    public ResponseEntity listAnswerAll() {
        ResponseModel response = this.ActiveFixedAssigment.listAnswerAll();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value = "activeInactive/{id}/{status}") //servicio actualizar
    public ResponseEntity activeInactive(@PathVariable int id,@PathVariable boolean status) {
        ResponseModel response = this.ActiveFixedAssigment.aprovedRejected(id,status);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value = "updateAnswer/{id}/{answer}") //servicio actualizar
    public ResponseEntity updateAnswer(@PathVariable int id, @PathVariable String answer) {
        ResponseModel response = this.ActiveFixedAssigment.updateAnswer(id, answer);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value = "aprovedRejected/{id}/{status}") //servicio actualizar
    public ResponseEntity aprovedRejected(@PathVariable int id, @PathVariable boolean status) {
        ResponseModel response = this.ActiveFixedAssigment.aprovedRejected(id, status);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value = "entryExit/{serial}/{status}") //servicio actualizar
    public ResponseEntity entryExit(@PathVariable String serial, @PathVariable boolean status) {
        ResponseModel response = this.ActiveFixedAssigment.entryExit(serial, status);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
     @GetMapping(value = "listExitVerif") //servicio mostrar en pantalla
    public ResponseEntity listExitVerif() {
        ResponseModel response = this.ActiveFixedAssigment.listExitVerif();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
     @GetMapping(value = "findByIdentification/{serial}") //servicio mostrar en pantalla
    public ResponseEntity findByIdentification(@PathVariable String serial) {
        ResponseModel response = this.ActiveFixedAssigment.findByIdentification(serial);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
      @PutMapping(value = "updateDate") //servicio actualizar
    public ResponseEntity updateDate(@RequestBody ActiveFixedAssigmentEntity a) {
        ResponseModel response = this.ActiveFixedAssigment.updateDate(a);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
     @GetMapping(value = "dateById/{id}")
    public ResponseEntity dateById(@PathVariable int id) {
        ResponseModel response = this.ActiveFixedAssigment.dateById(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
   
}
