/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.woden.wdsit.controller;

import java.io.IOException;
import java.sql.SQLException;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.ReportValidateLoadCountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author b.algecira
 */
@RestController
@RequestMapping("ReportValidateLoadCountryS/")
public class ReportValidateLoadCountryController {

    @Autowired
    private ReportValidateLoadCountryService ReportValidateLoadCountryS;

    @GetMapping(value = "listCountry/{country}")
    public ResponseEntity listCountry(@PathVariable String country) throws IOException, SQLException  {
        ResponseModel response = this.ReportValidateLoadCountryS.listCountry(country);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "listCountryRetry/{country}")
    public ResponseEntity listCountryRetry(@PathVariable String country) throws IOException {
        ResponseModel response = this.ReportValidateLoadCountryS.listCountryRetry(country);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value = "createLogOne/{country}") // servicio crear y almacenar
    public ResponseEntity createLogOne(@PathVariable String country) {
        ResponseModel response = this.ReportValidateLoadCountryS.createLogOne(country);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value = "createLogTwo/{country}") // servicio crear y almacenar
    public ResponseEntity createLogTwo(@PathVariable String country) {
        ResponseModel response = this.ReportValidateLoadCountryS.createLogTwo(country);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
