/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.woden.wdsit.controller;

import net.woden.wdsit.entity.RepFlagLogEntity;
import net.woden.wdsit.entity.RepReportCountryEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.RepReportCountryService;
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
@RequestMapping("RepReportCountryS/")

public class RepReportCountryController {

    @Autowired
    private RepReportCountryService RepReportCountryS;

    @PostMapping(value = "create") // servicio crear y almacenar
    public ResponseEntity create(@RequestBody RepReportCountryEntity r) {
        ResponseModel response = this.RepReportCountryS.create(r);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping(value = "delete/{countryId}/{reportId}")
    public ResponseEntity delete(@PathVariable int countryId, @PathVariable int reportId) {
        ResponseModel response = this.RepReportCountryS.delete(countryId, reportId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "list")
    public ResponseEntity list() {
        ResponseModel response = this.RepReportCountryS.list();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "findAll/{countryId}")
    public ResponseEntity findAll(@PathVariable int countryId) {
        ResponseModel response = this.RepReportCountryS.findAll(countryId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value = "update")
    public ResponseEntity update(@RequestBody RepFlagLogEntity r) {
        ResponseModel response = this.RepReportCountryS.update(r);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
      @GetMapping(value = "listActive")
    public ResponseEntity listActive() {
        ResponseModel response = this.RepReportCountryS.listActive();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
