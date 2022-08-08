/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.woden.wdsit.controller;

import net.woden.wdsit.entity.ScpCrossingWmsEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.ScpCrossingWmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @RequestMapping("ScpCrossingWmsS/")
public class ScpCrossingWmsController {
    
    @Autowired
    private ScpCrossingWmsService ScpCrossingWmsS;

    @PostMapping(value = "create") // servicio crear y almacenar
    public ResponseEntity create(@RequestBody ScpCrossingWmsEntity s ) {
        ResponseModel response = this.ScpCrossingWmsS.create(s);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value = "listCrossing/{auditPreviousId}")
    public ResponseEntity listCrossing(@PathVariable int auditPreviousId) {
        ResponseModel response = this.ScpCrossingWmsS.listCrossing(auditPreviousId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value = "listCrossingCount/{auditPreviousId}")
    public ResponseEntity listCrossingCount(@PathVariable int auditPreviousId) {
        ResponseModel response = this.ScpCrossingWmsS.listCrossingCount(auditPreviousId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
