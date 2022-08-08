/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.controller;

import net.woden.wdsit.entity.ComCustomerEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.ComCustomerService;
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
 * @author m.pulido
 */
@RestController
@RequestMapping("comCustomer/")
public class ComCustomerController {
    
    @Autowired
    private ComCustomerService CommCustomerS; 
    
    @PostMapping(value="create/{userId}")
    public ResponseEntity create(@PathVariable int userId ,@RequestBody ComCustomerEntity c ){
        ResponseModel response=this.CommCustomerS.create(userId, c); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @PutMapping(value="update/{comCustomerId}")
    public ResponseEntity creaupdatete(@PathVariable int comCustomerId ,@RequestBody ComCustomerEntity c ){
        ResponseModel response=this.CommCustomerS.update(comCustomerId, c); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @DeleteMapping(value="delete/{comCustomerId}")
    public ResponseEntity delete(@PathVariable int comCustomerId){
        ResponseModel response=this.CommCustomerS.delete(comCustomerId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @GetMapping(value="list")
    public ResponseEntity list(){
        ResponseModel response=this.CommCustomerS.list(); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
