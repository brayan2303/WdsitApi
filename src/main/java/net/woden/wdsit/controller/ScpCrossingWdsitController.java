/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.woden.wdsit.controller;

import net.woden.wdsit.entity.ScpCrossingWdsitEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.ScpCrossingWdsitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author b.algecira
 */
@RestController
@RequestMapping("ScpCrossingWdsitS/")
public class ScpCrossingWdsitController {
    
      @Autowired
    private ScpCrossingWdsitService ScpCrossingWdsitS;

    @PostMapping(value = "create") // servicio crear y almacenar
    public ResponseEntity create(@RequestBody String serial) {
        ResponseModel response = this.ScpCrossingWdsitS.create(serial);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
