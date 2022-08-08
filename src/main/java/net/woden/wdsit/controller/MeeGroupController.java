/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.controller;

import net.woden.wdsit.entity.MeeGroupEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.MeeGroupService;
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
@RequestMapping("MeeGroup/")
public class MeeGroupController {

    @Autowired
    private MeeGroupService MeeGroup;

    @PostMapping(value = "create") // servicio crear y almacenar
    public ResponseEntity create(@RequestBody MeeGroupEntity m) {
        ResponseModel response = this.MeeGroup.create(m);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping(value = "update")
    public ResponseEntity update(@RequestBody MeeGroupEntity m) {
        ResponseModel response = this.MeeGroup.update(m);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping(value = "delete/{id}")
    public ResponseEntity delete(@PathVariable int id) {
        ResponseModel response = this.MeeGroup.delete(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "list") //servicio mostrar en pantalla
    public ResponseEntity list() {
        ResponseModel response = this.MeeGroup.list();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
     @GetMapping("findById/{groupId}")
    public ResponseEntity findById(@PathVariable int groupId){
        ResponseModel response=this.MeeGroup.findById(groupId);
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
