/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.controller;

import net.woden.wdsit.entity.ComCommodityEntryArticlesPreviousEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.ComCommodityEntryArticlesPreviousService;
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
 * @author m.pulido
 */
@RestController
@RequestMapping("ComCommodityEntryArticlesPrevious/")
public class ComCommodityEntryArticlesPreviousController {
    
    @Autowired
    private ComCommodityEntryArticlesPreviousService ComCommodityEntryArticlesPreviousS;
    
    
    @PostMapping(value="create")
    public ResponseEntity create(@RequestBody ComCommodityEntryArticlesPreviousEntity c ){
        ResponseModel response=this.ComCommodityEntryArticlesPreviousS.create(c); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @GetMapping(value="listByEntryId/{entryId}")
    public ResponseEntity listByEntryId(@PathVariable int entryId){
        ResponseModel response=this.ComCommodityEntryArticlesPreviousS.listByEntryId(entryId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @DeleteMapping(value="delete/{articleId}")
    public ResponseEntity delete(@PathVariable int articleId){
        ResponseModel response=this.ComCommodityEntryArticlesPreviousS.delete(articleId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
