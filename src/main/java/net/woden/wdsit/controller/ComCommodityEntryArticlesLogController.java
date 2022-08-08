/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.controller;

import net.woden.wdsit.entity.ComCommodityEntryArticlesLogEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.ComCommodityEntryArticlesLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author m.pulido
 */
@RestController
@RequestMapping("ComCommodityEntryArticlesLog/")
public class ComCommodityEntryArticlesLogController {
    
    @Autowired
    private ComCommodityEntryArticlesLogService ComCommodityEntryArticlesLogS;
    
    @PostMapping(value="create")
    public ResponseEntity create(@RequestBody ComCommodityEntryArticlesLogEntity c ){
        ResponseModel response=this.ComCommodityEntryArticlesLogS.create(c); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
