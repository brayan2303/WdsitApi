/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.controller;

import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.ComCommodityEntryLoadArticlesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author m.pulido
 */
@RestController
@RequestMapping("ComCommodityEntryLoadArticles/")
public class ComCommodityEntryLoadArticlesController {
    
    @Autowired
    private ComCommodityEntryLoadArticlesService ComCommodityEntryLoadArticlesS;
    
    @PostMapping("validate/{loadId}")
    public ResponseEntity validate(@PathVariable int loadId, @RequestBody MultipartFile file) {
        ResponseModel response = this.ComCommodityEntryLoadArticlesS.validate( loadId, file);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @PostMapping("charge/{loadId}/{userId}")
    public ResponseEntity charge(@PathVariable int loadId, @PathVariable int userId) {
        ResponseModel response = this.ComCommodityEntryLoadArticlesS.charge(loadId, userId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @PostMapping("chargeLoad/{loadId}")
    public ResponseEntity chargeLoad(@PathVariable int loadId) {
        ResponseModel response = this.ComCommodityEntryLoadArticlesS.chargeLoad(loadId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @GetMapping(value="listByEntryId/{entryId}")
    public ResponseEntity listByEntryId(@PathVariable int entryId){
        ResponseModel response=this.ComCommodityEntryLoadArticlesS.listByEntryId(entryId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
