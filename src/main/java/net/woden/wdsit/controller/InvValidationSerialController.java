/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.woden.wdsit.controller;

import net.woden.wdsit.entity.InvValidationSerialEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.InvValidationSerialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("InvValidationSerialService/")
public class InvValidationSerialController {
    
    @Autowired
    private InvValidationSerialService InvValidationSerialS;

    @PostMapping(value = "create") // servicio crear y almacenar
    public ResponseEntity create(@RequestBody InvValidationSerialEntity i) {
        ResponseModel response = this.InvValidationSerialS.create(i);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
      @GetMapping(value = "findByValidation/{typeId}/{typeCounting}/{serial}")
    public ResponseEntity findByValidation(@PathVariable int typeId, @PathVariable String typeCounting, @PathVariable String serial) {
        ResponseModel response = this.InvValidationSerialS.findByValidation(typeId, typeCounting, serial);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value = "delete/{typeId}/{typeCounting}/{serial}") //servicio eliminar
    public ResponseEntity delete(@PathVariable int typeId, @PathVariable String typeCounting,@PathVariable String serial) {
        ResponseModel response = this.InvValidationSerialS.delete(typeId,typeCounting,serial);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }


    
}
