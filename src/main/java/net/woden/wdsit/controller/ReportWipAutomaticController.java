/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.woden.wdsit.controller;

import java.io.IOException;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.ReportWipAutomaticService;
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
@RequestMapping("ReportWipAutomaticS/")
public class ReportWipAutomaticController {

    @Autowired
    private ReportWipAutomaticService ReportWipAutomaticS;

    @GetMapping(value = "generateWip")
    public ResponseEntity generateWip() throws IOException {
        ResponseModel response = this.ReportWipAutomaticS.generateWip();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "deleteFiles")
    public ResponseEntity deleteFiles() {
        ResponseModel response = this.ReportWipAutomaticS.deleteFiles();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
