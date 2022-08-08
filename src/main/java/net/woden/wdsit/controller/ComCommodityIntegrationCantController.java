/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.woden.wdsit.controller;

import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.ComCommodityIntegrationCantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author b.algecira
 */
@RestController
@RequestMapping("ComCommodityIntegrationCantS/")
public class ComCommodityIntegrationCantController {
    
     @Autowired
    private ComCommodityIntegrationCantService ComCommodityIntegrationCantS; 
    
     @GetMapping(value="list/{pallet}")
    public ResponseEntity list(@PathVariable String pallet){
        ResponseModel response=this.ComCommodityIntegrationCantS.list(pallet); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @GetMapping(value="sapCodeList/{customerId}/{countryId}")
    public ResponseEntity sapCodeList(@PathVariable int customerId, @PathVariable int countryId){
        ResponseModel response=this.ComCommodityIntegrationCantS.sapCodeList(customerId,countryId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
