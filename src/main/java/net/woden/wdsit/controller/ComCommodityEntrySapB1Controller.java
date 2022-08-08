/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.controller;

import net.woden.wdsit.entity.ComCommodityEntrySapB1Entity;
import net.woden.wdsit.model.ComEntrySapListModel;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.ComCommodityEntrySapB1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("ComCommodityEntry/load/")
public class ComCommodityEntrySapB1Controller {
    
    @Autowired
    private ComCommodityEntrySapB1Service ComCommodityEntrySapB1S;

    @GetMapping(value="locationList/{countryId}/{coustomerId}")
    public ResponseEntity originList(@PathVariable int countryId, @PathVariable int coustomerId){
        ResponseModel response=this.ComCommodityEntrySapB1S.locationList(countryId,coustomerId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @GetMapping(value="findByEntryId/{entryId}")
    public ResponseEntity findByEntryId(@PathVariable int entryId){
        ResponseModel response=this.ComCommodityEntrySapB1S.findByEntryId(entryId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @PostMapping(value="addEntryToSap")
    public ResponseEntity addEntryToSap(@RequestBody ComEntrySapListModel c ){
        ResponseModel response=this.ComCommodityEntrySapB1S.addEntryToSap(c); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @PostMapping(value="create")
    public ResponseEntity create(@RequestBody ComCommodityEntrySapB1Entity c ){
        ResponseModel response=this.ComCommodityEntrySapB1S.create(c); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @PutMapping(value="update")
    public ResponseEntity update(@RequestBody ComCommodityEntrySapB1Entity c ){
        ResponseModel response=this.ComCommodityEntrySapB1S.update(c); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
