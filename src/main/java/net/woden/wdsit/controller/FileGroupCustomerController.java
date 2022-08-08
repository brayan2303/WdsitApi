/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.woden.wdsit.controller;

import net.woden.wdsit.entity.FileGroupCustomerEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.FileGroupCustomerService;
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
@RequestMapping("FileGroupCustomer/")
public class FileGroupCustomerController {
    @Autowired
    private FileGroupCustomerService FileGroupCustomer;
    
    @PostMapping(value = "create")
    public ResponseEntity create(@RequestBody FileGroupCustomerEntity a) {
        ResponseModel response = this.FileGroupCustomer.create(a);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @DeleteMapping(value = "delete/{groupId}/{customerId}")
    public ResponseEntity delete(@PathVariable int groupId, @PathVariable int customerId) {
        ResponseModel response = this.FileGroupCustomer.delete(groupId, customerId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @GetMapping(value = "list/{groupId}")
    public ResponseEntity list(@PathVariable int groupId) {
        ResponseModel response = this.FileGroupCustomer.list(groupId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
