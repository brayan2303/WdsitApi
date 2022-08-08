/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.controller;

import net.woden.wdsit.entity.LoadPersonCustomerEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.LoadPersonCustomerService;
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

/**
 *
 * @author b.algecira
 */
@RestController
@RequestMapping("LoadPersonCustomer/")

public class LoadPersonCustomerController {

    @Autowired
    private LoadPersonCustomerService LoadPersonCustomer;

    @PostMapping(value = "create") // servicio crear y almacenar
    public ResponseEntity create(@RequestBody LoadPersonCustomerEntity a) {
        ResponseModel response = this.LoadPersonCustomer.create(a);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping(value = "delete/{personId}/{customerId}")
    public ResponseEntity delete(@PathVariable int personId, @PathVariable int customerId) {
        ResponseModel response = this.LoadPersonCustomer.delete(personId, customerId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "list") //servicio mostrar en pantalla
    public ResponseEntity list() {
        ResponseModel response = this.LoadPersonCustomer.list();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "findAll/{personId}")
    public ResponseEntity findAll(@PathVariable int personId) {
        ResponseModel response = this.LoadPersonCustomer.findAll(personId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "findCustomerByPersonId/{personId}")
    public ResponseEntity findCustomerByPersonId(@PathVariable int personId) {
        ResponseModel response = this.LoadPersonCustomer.findCustomerByPersonId(personId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "findCustomerByPersonIdList/{personId}")
    public ResponseEntity findCustomerByPersonIdList(@PathVariable int personId) {
        ResponseModel response = this.LoadPersonCustomer.findCustomerByPersonIdList(personId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
