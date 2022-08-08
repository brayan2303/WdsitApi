/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.woden.wdsit.controller;

import net.woden.wdsit.entity.InvMasterSerialTigoEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.InvMasterSerialTigoService;
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
@RequestMapping("InvMasterSerialTigoS/")
public class InvMasterSerialTigoController {

    @Autowired
    private InvMasterSerialTigoService InvMasterSerialTigoS;

    @PostMapping(value = "create/{userId}")
    public ResponseEntity create(@RequestBody InvMasterSerialTigoEntity i, @PathVariable int userId) {
        ResponseModel response = this.InvMasterSerialTigoS.create(i, userId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping(value = "delete/{id}") //servicio eliminar
    public ResponseEntity delete(@PathVariable int id) {
        ResponseModel response = this.InvMasterSerialTigoS.delete(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "list/{palletId}")
    public ResponseEntity list(@PathVariable int palletId) {
        ResponseModel response = this.InvMasterSerialTigoS.list(palletId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "validationRR/{serial}/{codigoSap}")
    public ResponseEntity validationRR(@PathVariable String serial, @PathVariable String codigoSap) {
        ResponseModel response = this.InvMasterSerialTigoS.validationRR(serial, codigoSap);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "validationRRSerial/{serial}")
    public ResponseEntity validationRRSerial(@PathVariable String serial) {
        ResponseModel response = this.InvMasterSerialTigoS.validationRRSerial(serial);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "validationMissing/{serial}")
    public ResponseEntity validationMissing(@PathVariable String serial) {
        ResponseModel response = this.InvMasterSerialTigoS.validationMissing(serial);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "validationSpare/{serial}")
    public ResponseEntity validationSpare(@PathVariable String serial) {
        ResponseModel response = this.InvMasterSerialTigoS.validationSpare(serial);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "validationSerial/{serial}")
    public ResponseEntity validationSerial(@PathVariable String serial) {
        ResponseModel response = this.InvMasterSerialTigoS.validationSerial(serial);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping(value = "update/{id}") //servicio actualizar
    public ResponseEntity update(@PathVariable int id) {
        ResponseModel response = this.InvMasterSerialTigoS.update(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "missingCount")
    public ResponseEntity missingCount() {
        ResponseModel response = this.InvMasterSerialTigoS.missingCount();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "missingCountFound")
    public ResponseEntity missingCountFound() {
        ResponseModel response = this.InvMasterSerialTigoS.missingCountFound();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "spareCount")
    public ResponseEntity spareCount() {
        ResponseModel response = this.InvMasterSerialTigoS.spareCount();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "spareCountFound")
    public ResponseEntity spareCountFound() {
        ResponseModel response = this.InvMasterSerialTigoS.spareCountFound();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
