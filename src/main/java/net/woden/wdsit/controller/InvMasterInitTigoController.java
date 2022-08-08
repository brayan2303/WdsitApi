/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.woden.wdsit.controller;

import net.woden.wdsit.entity.InvMasterInitTigoEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.InvMasterInitTigoService;
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
@RequestMapping("InvMasterInitTigoS/")
public class InvMasterInitTigoController {

    @Autowired
    private InvMasterInitTigoService InvMasterInitTigoS;

    @PostMapping(value = "create/{userId}")
    public ResponseEntity create(@RequestBody InvMasterInitTigoEntity i, @PathVariable int userId) {
        ResponseModel response = this.InvMasterInitTigoS.create(i, userId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping(value = "update") //servicio actualizar
    public ResponseEntity update(@RequestBody InvMasterInitTigoEntity i, @PathVariable int userId) {
        ResponseModel response = this.InvMasterInitTigoS.update(i, userId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping(value = "delete/{id}") //servicio eliminar
    public ResponseEntity delete(@PathVariable int id) {
        ResponseModel response = this.InvMasterInitTigoS.delete(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "list/{userId}")
    public ResponseEntity list(@PathVariable int userId) {
        ResponseModel response = this.InvMasterInitTigoS.list(userId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "findById/{id}")
    public ResponseEntity findById(@PathVariable int id) {
        ResponseModel response = this.InvMasterInitTigoS.findByid(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "listMasterType/{type}")
    public ResponseEntity listMasterType(@PathVariable String type) {
        ResponseModel response = this.InvMasterInitTigoS.listMasterType(type);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "listByAudit")
    public ResponseEntity listByAudit() {
        ResponseModel response = this.InvMasterInitTigoS.listByAudit();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value = "changeState/{palletId}/{state}/{userId}")
    public ResponseEntity changeState(@PathVariable int palletId, @PathVariable String state, @PathVariable int userId) {
        ResponseModel response = this.InvMasterInitTigoS.changeState(palletId, state, userId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "generatePallet/{palletId}")
    public ResponseEntity generatePallet(@PathVariable String palletId) {
        ResponseModel response = this.InvMasterInitTigoS.generatePallet(palletId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "findQuantity/{palletId}")
    public ResponseEntity findQuantity(@PathVariable int palletId) {
        ResponseModel response = this.InvMasterInitTigoS.findQuantity(palletId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "pallet/{userId}")
    public ResponseEntity pallet(@PathVariable int userId) {
        ResponseModel response = this.InvMasterInitTigoS.pallet(userId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "validationPallet/{palletId}")
    public ResponseEntity validationPallet(@PathVariable int palletId) {
        ResponseModel response = this.InvMasterInitTigoS.validationPallet(palletId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "serialCount/{status}/{palletId}")
    public ResponseEntity serialCount(@PathVariable String status, @PathVariable int palletId) {
        ResponseModel response = this.InvMasterInitTigoS.serialCount(status, palletId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping(value = "updateLocation")
    public ResponseEntity updateLocation(@RequestBody InvMasterInitTigoEntity i) {
        ResponseModel response = this.InvMasterInitTigoS.updateLocation(i);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "personAll")
    public ResponseEntity personAll() {
        ResponseModel response = this.InvMasterInitTigoS.personAll();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping(value = "updatePerson")
    public ResponseEntity updatePerson(@RequestBody InvMasterInitTigoEntity i) {
        ResponseModel response = this.InvMasterInitTigoS.updatePerson(i);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "listAll")
    public ResponseEntity listAll() {
        ResponseModel response = this.InvMasterInitTigoS.listAll();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "validationCodSap/{codSap}/{serial}")
    public ResponseEntity validationCodSap(@PathVariable String codSap, @PathVariable String serial) {
        ResponseModel response = this.InvMasterInitTigoS.validationCodSap(codSap, serial);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
