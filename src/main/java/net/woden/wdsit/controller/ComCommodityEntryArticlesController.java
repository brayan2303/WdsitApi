/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.controller;

import net.woden.wdsit.entity.ComCommodityEntryArticlesEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.ComCommodityEntryArticlesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author m.pulido
 */
@RestController
@RequestMapping("ComCommodityEntryArticles/")
public class ComCommodityEntryArticlesController {
    
    @Autowired
    private ComCommodityEntryArticlesService ComCommodityEntryArticlesS;
    
    @GetMapping(value="listByEntryId/{entryId}")
    public ResponseEntity listByEntryId(@PathVariable int entryId){
        ResponseModel response=this.ComCommodityEntryArticlesS.listByEntryId(entryId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @GetMapping(value="listByEntryNumber/{entryNumber}")
    public ResponseEntity listByEntryNumber(@PathVariable String entryNumber){
        ResponseModel response=this.ComCommodityEntryArticlesS.listByEntryNumber(entryNumber); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @PutMapping(value="update")
    public ResponseEntity update(@RequestBody ComCommodityEntryArticlesEntity e){
        ResponseModel response=this.ComCommodityEntryArticlesS.update(e); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
