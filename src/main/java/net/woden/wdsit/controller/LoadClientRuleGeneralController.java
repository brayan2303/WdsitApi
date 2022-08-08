/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.woden.wdsit.controller;

import java.io.IOException;
import java.sql.SQLException;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.LoadClientRuleGeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("LoadClientRuleGeneralS/")
public class LoadClientRuleGeneralController {

    @Autowired
    private LoadClientRuleGeneralService LoadClientRuleGeneralS;

    @GetMapping(value = "createHughes")
    public ResponseEntity createHughes() throws SQLException, IOException {
        ResponseModel response = this.LoadClientRuleGeneralS.createHughes();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "createRedExterna")
    public ResponseEntity createRedExterna() throws SQLException {
        ResponseModel response = this.LoadClientRuleGeneralS.createRedExterna();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "createPlataformaMovil")
    public ResponseEntity createPlataformaMovil() throws SQLException {
        ResponseModel response = this.LoadClientRuleGeneralS.createPlataformaMovil();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "createEtb")
    public ResponseEntity createEtb() throws SQLException, IOException {
        ResponseModel response = this.LoadClientRuleGeneralS.createEtb();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "createDirectv")
    public ResponseEntity createDirectv() throws SQLException, IOException {
        ResponseModel response = this.LoadClientRuleGeneralS.createDirectv();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "createClaro")
    public ResponseEntity createClaro() throws SQLException, IOException {
        ResponseModel response = this.LoadClientRuleGeneralS.createClaro();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "createTigo")
    public ResponseEntity createTigo() throws SQLException, IOException {
        ResponseModel response = this.LoadClientRuleGeneralS.createTigo();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("sendEmail/{destinatarioId}")
    public ResponseEntity sendEmail(@PathVariable int destinatarioId) {
        ResponseModel response = this.LoadClientRuleGeneralS.sendEmail(destinatarioId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "listRedExterna")
    public ResponseEntity listRedExterna() throws IOException {
        ResponseModel response = this.LoadClientRuleGeneralS.listRedExterna();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @GetMapping(value = "listPlataformaMovil")
    public ResponseEntity listPlataformaMovil() throws IOException {
        ResponseModel response = this.LoadClientRuleGeneralS.listPlataformaMovil();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value = "delete/{customer}")
    public ResponseEntity delete(@PathVariable String customer) {
        ResponseModel response = this.LoadClientRuleGeneralS.delete(customer);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
     @GetMapping(value = "listGeneral")
    public ResponseEntity listGeneral(){
        ResponseModel response = this.LoadClientRuleGeneralS.listGeneral();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
