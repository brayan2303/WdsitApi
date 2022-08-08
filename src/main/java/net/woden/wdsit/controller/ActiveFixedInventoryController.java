/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.controller;

import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.ActiveFixedInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author b.algecira
 */
@RestController
@RequestMapping("ActiveFixedInventory/")

public class ActiveFixedInventoryController {
    
      @Autowired
    private ActiveFixedInventoryService ActiveFixedInventory;
      
      @GetMapping(value = "listEntryTotal") //servicio mostrar en pantalla
    public ResponseEntity listEntryTotal() {
        ResponseModel response = this.ActiveFixedInventory.listEntryTotal();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value = "listEntry") //servicio mostrar en pantalla
    public ResponseEntity listEntry() {
        ResponseModel response = this.ActiveFixedInventory.listEntry();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value = "listEntryExit") //servicio mostrar en pantalla
    public ResponseEntity listEntryExit() {
        ResponseModel response = this.ActiveFixedInventory.listEntryExit();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
      @GetMapping(value = "listAnswerAproved") //servicio mostrar en pantalla
    public ResponseEntity listAnswerAproved() {
        ResponseModel response = this.ActiveFixedInventory.listAnswerAproved();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
      @GetMapping(value = "listAnswerRejected") //servicio mostrar en pantalla
    public ResponseEntity listAnswerRejected() {
        ResponseModel response = this.ActiveFixedInventory.listAnswerRejected();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value = "listRejected") //servicio mostrar en pantalla
    public ResponseEntity listRejected() {
        ResponseModel response = this.ActiveFixedInventory.listRejected();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value = "listAproved") //servicio mostrar en pantalla
    public ResponseEntity listAproved() {
        ResponseModel response = this.ActiveFixedInventory.listAproved();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
        @GetMapping(value = "listCompany") //servicio mostrar en pantalla
    public ResponseEntity listCompany() {
        ResponseModel response = this.ActiveFixedInventory.listCompany();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
       @GetMapping(value = "listFlow") //servicio mostrar en pantalla
    public ResponseEntity listFlow() {
        ResponseModel response = this.ActiveFixedInventory.listFlow();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
      @GetMapping(value = "countAys") //servicio mostrar en pantalla
    public ResponseEntity countAys() {
        ResponseModel response = this.ActiveFixedInventory.countAys();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
      @GetMapping(value = "countAlqui") //servicio mostrar en pantalla
    public ResponseEntity countAlqui() {
        ResponseModel response = this.ActiveFixedInventory.countAlqui();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
      @GetMapping(value = "countRenta") //servicio mostrar en pantalla
    public ResponseEntity countRenta() {
        ResponseModel response = this.ActiveFixedInventory.countRenta();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
      @GetMapping(value = "countWoden") //servicio mostrar en pantalla
    public ResponseEntity countWoden() {
        ResponseModel response = this.ActiveFixedInventory.countWoden();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value = "listAys") //servicio mostrar en pantalla
    public ResponseEntity listAys() {
        ResponseModel response = this.ActiveFixedInventory.listAys();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value = "listRent") //servicio mostrar en pantalla
    public ResponseEntity listRent() {
        ResponseModel response = this.ActiveFixedInventory.listRent();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value = "listAlqui") //servicio mostrar en pantalla
    public ResponseEntity listAlqui() {
        ResponseModel response = this.ActiveFixedInventory.listAlqui();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value = "listWoden") //servicio mostrar en pantalla
    public ResponseEntity listWoden() {
        ResponseModel response = this.ActiveFixedInventory.listWoden();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    
}
