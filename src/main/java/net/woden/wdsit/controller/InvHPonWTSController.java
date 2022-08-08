/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.woden.wdsit.controller;

import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.InvHPonWTSService;
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
@RequestMapping("InvHPonWTSS/")
public class InvHPonWTSController {

    @Autowired
    private InvHPonWTSService InvHPonWTSS;

    @GetMapping(value = "InvHPonWTSLocation")
    public ResponseEntity InvHPonWTSLocation() {
        ResponseModel response = this.InvHPonWTSS.InvHPonWTSLocation();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
     @GetMapping(value = "InvHPonWTSWarehouse")
    public ResponseEntity InvHPonWTSWarehouse() {
        ResponseModel response = this.InvHPonWTSS.InvHPonWTSWarehouse();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
