/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.woden.wdsit.controller;

import net.woden.wdsit.entity.InvMasterInitEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.InvMasterInitService;
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
@RequestMapping("InvMasterInitS/")
public class InvMasterInitController {

    @Autowired
    private InvMasterInitService InvMasterInitS;

    @PostMapping(value = "create/{userId}")
    public ResponseEntity create(@RequestBody InvMasterInitEntity i, @PathVariable int userId) {
        ResponseModel response = this.InvMasterInitS.create(i, userId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping(value = "update") //servicio actualizar
    public ResponseEntity update(@RequestBody InvMasterInitEntity i, @PathVariable int userId) {
        ResponseModel response = this.InvMasterInitS.update(i, userId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping(value = "delete/{id}") //servicio eliminar
    public ResponseEntity delete(@PathVariable int id) {
        ResponseModel response = this.InvMasterInitS.delete(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "list/{userId}")
    public ResponseEntity list(@PathVariable int userId) {
        ResponseModel response = this.InvMasterInitS.list(userId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "findById/{id}")
    public ResponseEntity findById(@PathVariable int id) {
        ResponseModel response = this.InvMasterInitS.findByid(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "listMasterType/{type}")
    public ResponseEntity listMasterType(@PathVariable String type) {
        ResponseModel response = this.InvMasterInitS.listMasterType(type);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "listByAudit")
    public ResponseEntity listByAudit() {
        ResponseModel response = this.InvMasterInitS.listByAudit();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value = "changeState/{palletId}/{state}/{userId}")
    public ResponseEntity changeState(@PathVariable int palletId, @PathVariable String state, @PathVariable int userId) {
        ResponseModel response = this.InvMasterInitS.changeState(palletId, state, userId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "generatePallet/{palletId}")
    public ResponseEntity generatePallet(@PathVariable String palletId) {
        ResponseModel response = this.InvMasterInitS.generatePallet(palletId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "findQuantity/{palletId}")
    public ResponseEntity findQuantity(@PathVariable int palletId) {
        ResponseModel response = this.InvMasterInitS.findQuantity(palletId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "pallet/{userId}")
    public ResponseEntity pallet(@PathVariable int userId) {
        ResponseModel response = this.InvMasterInitS.pallet(userId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "validationPallet/{palletId}")
    public ResponseEntity validationPallet(@PathVariable int palletId) {
        ResponseModel response = this.InvMasterInitS.validationPallet(palletId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "serialCount/{status}/{palletId}")
    public ResponseEntity serialCount(@PathVariable String status, @PathVariable int palletId) {
        ResponseModel response = this.InvMasterInitS.serialCount(status, palletId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping(value = "updateLocation")
    public ResponseEntity updateLocation(@RequestBody InvMasterInitEntity i) {
        ResponseModel response = this.InvMasterInitS.updateLocation(i);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "personAll")
    public ResponseEntity personAll() {
        ResponseModel response = this.InvMasterInitS.personAll();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping(value = "updatePerson")
    public ResponseEntity updatePerson(@RequestBody InvMasterInitEntity i) {
        ResponseModel response = this.InvMasterInitS.updatePerson(i);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "listAll")
    public ResponseEntity listAll() {
        ResponseModel response = this.InvMasterInitS.listAll();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
