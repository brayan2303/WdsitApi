/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.woden.wdsit.controller;

import net.woden.wdsit.entity.InvPartNumberDeftGeneralEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.InvPartNumberDeftGeneralService;
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
@RequestMapping("InvPartNumberDeftGeneralS/")
public class InvPartNumberDeftGeneralController {

    @Autowired
    private InvPartNumberDeftGeneralService InvPartNumberDeftGeneralS;

    @PostMapping(value = "create/{userId}")
    public ResponseEntity create(@RequestBody InvPartNumberDeftGeneralEntity i, @PathVariable int userId) {
        ResponseModel response = this.InvPartNumberDeftGeneralS.create(i, userId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value = "list/{id}")
    public ResponseEntity list(@PathVariable int id) {
        ResponseModel response = this.InvPartNumberDeftGeneralS.list(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value = "delete/{id}") //servicio eliminar
    public ResponseEntity delete(@PathVariable int id) {
        ResponseModel response = this.InvPartNumberDeftGeneralS.delete(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
