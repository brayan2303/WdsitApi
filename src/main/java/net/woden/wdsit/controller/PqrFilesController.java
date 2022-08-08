/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.controller;

import net.woden.wdsit.entity.PqrFilesEnity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.PqrFilesService;
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
@RequestMapping("PqrFilesS/")
public class PqrFilesController {

    @Autowired
    private PqrFilesService PqrFilesS;

    @PostMapping(value = "create/{countryId}") // servicio crear y almacenar
    public ResponseEntity create(@RequestBody PqrFilesEnity p, @PathVariable int countryId) {
        ResponseModel response = this.PqrFilesS.create(p, countryId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping(value = "update") //servicio actualizar
    public ResponseEntity update(@RequestBody PqrFilesEnity p) {
        ResponseModel response = this.PqrFilesS.update(p);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping(value = "delete/{id}") //servicio eliminar
    public ResponseEntity delete(@PathVariable int id) {
        ResponseModel response = this.PqrFilesS.delete(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "list/{countryId}") //servicio mostrar en pantalla
    public ResponseEntity list(@PathVariable int countryId) {
        ResponseModel response = this.PqrFilesS.list(countryId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "findById/{id}")
    public ResponseEntity findById(@PathVariable int id) {
        ResponseModel response = this.PqrFilesS.findById(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "findAll/{name}/{countryId}")
    public ResponseEntity findAll(@PathVariable String name, @PathVariable int countryId) {
        ResponseModel response = this.PqrFilesS.findAll(name, countryId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "findAllCustomer/{name}/{countryId}")
    public ResponseEntity findAllCustomer(@PathVariable String name, @PathVariable int countryId) {
        ResponseModel response = this.PqrFilesS.findAllCustomer(name, countryId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value = "loadFile/{name}")
    public ResponseEntity loadFile(@PathVariable String name, @RequestParam("files") MultipartFile[] files) {
        ResponseModel response = this.PqrFilesS.loadFile(name, files);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "listFile/{name}")
    public ResponseEntity listFile(@PathVariable String name) {
        ResponseModel response = this.PqrFilesS.listFile(name);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
