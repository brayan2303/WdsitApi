/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.woden.wdsit.controller;

import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.InvMasterSerialTigoDashService;
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
@RequestMapping("InvMasterSerialTigoDashS/")
public class InvMasterSerialTigoDashController {

    @Autowired
    private InvMasterSerialTigoDashService InvMasterSerialTigoDashS;

    @GetMapping(value = "invStatus") //servicio mostrar en pantalla
    public ResponseEntity invStatus() {
        ResponseModel response = this.InvMasterSerialTigoDashS.invStatus();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "listStatus") //servicio mostrar en pantalla
    public ResponseEntity listStatus() {
        ResponseModel response = this.InvMasterSerialTigoDashS.listStatus();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "listPerson") //servicio mostrar en pantalla
    public ResponseEntity listPerson() {
        ResponseModel response = this.InvMasterSerialTigoDashS.listPerson();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
