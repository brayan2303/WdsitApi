/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.woden.wdsit.controller;

import net.woden.wdsit.entity.FileGroupEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.FileGroupService;
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
 * @author A.PULIDO
 */
@RestController
@RequestMapping("FileGroup/")

public class FileGroupController {
    @Autowired
    private FileGroupService FileGroup;
    
    @PostMapping(value = "create/{userId}")
    public ResponseEntity create(@RequestBody FileGroupEntity fg, @PathVariable int userId){
        ResponseModel response = this.FileGroup.create(fg, userId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);        
    }
    
    
    @PutMapping(value = "update/{userId}")
    public ResponseEntity update(@RequestBody FileGroupEntity fg, @PathVariable int userId){
        ResponseModel response = this.FileGroup.update(fg, userId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);        
    }
    
    @DeleteMapping(value = "delete/{id}")
    public ResponseEntity delete(@PathVariable int id){
        ResponseModel response = this.FileGroup.delete(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @GetMapping(value="list")
    public ResponseEntity list(){
        ResponseModel response = this.FileGroup.list();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @GetMapping(value="findById/{id}")
    public ResponseEntity findById(@PathVariable int id){
        ResponseModel response = this.FileGroup.findById(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
