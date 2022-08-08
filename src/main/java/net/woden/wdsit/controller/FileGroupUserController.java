/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.woden.wdsit.controller;

import net.woden.wdsit.entity.FileGroupUserEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.FileGroupUserService;
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
 * @author A.PULIDO
 */
@RestController
@RequestMapping("FileGroupUser/")
public class FileGroupUserController {
    @Autowired
    private FileGroupUserService FileGroupUser;
    
    @PostMapping(value = "create")
    public ResponseEntity create(@RequestBody FileGroupUserEntity a) {
        ResponseModel response = this.FileGroupUser.create(a);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @DeleteMapping(value = "delete/{userId}/{groupId}")
    public ResponseEntity delete(@PathVariable int userId, @PathVariable int groupId) {
        ResponseModel response = this.FileGroupUser.delete(groupId, userId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @GetMapping(value = "list/{userId}")
    public ResponseEntity list(@PathVariable int userId) {
        ResponseModel response = this.FileGroupUser.list(userId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
