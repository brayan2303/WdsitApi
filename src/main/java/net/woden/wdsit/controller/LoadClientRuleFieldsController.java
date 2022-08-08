/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.woden.wdsit.controller;

import net.woden.wdsit.entity.LoadClientRuleFieldsEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.LoadClientRuleFieldsService;
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
 * @author b.algecira
 */
@RestController
@RequestMapping("LoadClientRuleFieldsS/")
public class LoadClientRuleFieldsController {

    @Autowired
    private LoadClientRuleFieldsService LoadClientRuleFieldsS;

    @PostMapping(value = "create")
    public ResponseEntity create(@RequestBody LoadClientRuleFieldsEntity l) {
        ResponseModel response = this.LoadClientRuleFieldsS.create(l);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping(value = "update") //servicio actualizar
    public ResponseEntity update(@RequestBody LoadClientRuleFieldsEntity l) {
        ResponseModel response = this.LoadClientRuleFieldsS.update(l);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping(value = "delete/{id}") //servicio eliminar
    public ResponseEntity delete(@PathVariable int id) {
        ResponseModel response = this.LoadClientRuleFieldsS.delete(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

   @GetMapping(value = "list/{id}")
    public ResponseEntity list(@PathVariable int id) {
        ResponseModel response = this.LoadClientRuleFieldsS.list(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "findById/{id}")
    public ResponseEntity findById(@PathVariable int id) {
        ResponseModel response = this.LoadClientRuleFieldsS.findById(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
