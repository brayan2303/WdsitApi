/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.woden.wdsit.controller;

import net.woden.wdsit.entity.InvGeneralInitEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.InvGeneralInitService;
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
@RequestMapping("InvGeneralInitS/")
public class InvGeneralInitController {

    @Autowired
    private InvGeneralInitService InvGeneralInitS;

    @PostMapping(value = "create/{userId}")
    public ResponseEntity create(@RequestBody InvGeneralInitEntity i, @PathVariable int userId) {
        ResponseModel response = this.InvGeneralInitS.create(i, userId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping(value = "delete/{id}") //servicio eliminar
    public ResponseEntity delete(@PathVariable int id) {
        ResponseModel response = this.InvGeneralInitS.delete(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "list")
    public ResponseEntity list() {
        ResponseModel response = this.InvGeneralInitS.list();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
      @GetMapping(value = "listDeft")
    public ResponseEntity listDeft() {
        ResponseModel response = this.InvGeneralInitS.listDeft();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "findById/{id}")
    public ResponseEntity findById(@PathVariable int id) {
        ResponseModel response = this.InvGeneralInitS.findById(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "findByValidation/{countingType}/{goodDeft}/{store}/{parameterizationId}")
    public ResponseEntity findByValidation(@PathVariable String countingType, @PathVariable String goodDeft, @PathVariable String store, @PathVariable String parameterizationId) {
        ResponseModel response = this.InvGeneralInitS.findByValidation(countingType, goodDeft, store,parameterizationId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping(value = "update/{id}") //servicio actualizar
    public ResponseEntity update(@PathVariable int id) {
        ResponseModel response = this.InvGeneralInitS.update(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "listWarehouse")
    public ResponseEntity listWarehouse() {
        ResponseModel response = this.InvGeneralInitS.listWarehouse();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
     @GetMapping(value = "listWarehouseDeft")
    public ResponseEntity listWarehouseDeft() {
        ResponseModel response = this.InvGeneralInitS.listWarehouseDeft();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "listPartNumber")
    public ResponseEntity listPartNumber() {
        ResponseModel response = this.InvGeneralInitS.listPartNumber();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @GetMapping(value = "listPartNumberSerial/{partNumber}/{serial}")
    public ResponseEntity listPartNumberSerial(@PathVariable String partNumber,@PathVariable String serial) {
        ResponseModel response = this.InvGeneralInitS.listPartNumberSerial(partNumber, serial);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "findByIdCondition/{id}")
    public ResponseEntity findByIdCondition(@PathVariable int id) {
        ResponseModel response = this.InvGeneralInitS.findByIdCondition(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "findByIdQuantity/{id}")
    public ResponseEntity findByIdQuantity(@PathVariable int id) {
        ResponseModel response = this.InvGeneralInitS.findByIdQuantity(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "listLocation/{warehouse}")
    public ResponseEntity listLocation( @PathVariable String warehouse) {
        ResponseModel response = this.InvGeneralInitS.listLocation(warehouse);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping(value = "updateCounting/{id}") //servicio actualizar
    public ResponseEntity updateCounting(@PathVariable int id) {
        ResponseModel response = this.InvGeneralInitS.updateCounting(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
      @PutMapping(value = "updateCountingActive/{id}") //servicio actualizar
    public ResponseEntity updateCountingActive(@PathVariable int id) {
        ResponseModel response = this.InvGeneralInitS.updateCountingActive(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "findByValidationSerial/{serial}/{id}")
    public ResponseEntity findByValidationSerial(@PathVariable String serial, @PathVariable int id) {
        ResponseModel response = this.InvGeneralInitS.findByValidationSerial(serial, id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "findByValidationDeft/{countingType}/{goodDeft}/{parameterizationId}")
    public ResponseEntity findByValidationDeft(@PathVariable String countingType, @PathVariable String goodDeft, @PathVariable String parameterizationId) {
        ResponseModel response = this.InvGeneralInitS.findByValidationDeft(countingType, goodDeft, parameterizationId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value = "findByValidationPartNumber/{serial}")
    public ResponseEntity findByValidationPartNumber(@PathVariable String serial) {
        ResponseModel response = this.InvGeneralInitS.findByValidationPartNumber(serial);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
      @GetMapping(value = "listPerson")
    public ResponseEntity listPerson() {
        ResponseModel response = this.InvGeneralInitS.listPerson();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
       @GetMapping(value = "listInvHPonWTSDiv")
    public ResponseEntity listInvHPonWTSDiv() {
        ResponseModel response = this.InvGeneralInitS.listInvHPonWTSDiv();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
       @GetMapping(value = "listInvHPonWTSPart")
    public ResponseEntity listInvHPonWTSPart() {
        ResponseModel response = this.InvGeneralInitS.listInvHPonWTSPart();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
       @GetMapping(value = "listCrossing")
    public ResponseEntity listPlistCrossingerson() {
        ResponseModel response = this.InvGeneralInitS.listCrossing();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
      @GetMapping(value = "listInvDash")
    public ResponseEntity listInvDash() {
        ResponseModel response = this.InvGeneralInitS.listInvDash();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
