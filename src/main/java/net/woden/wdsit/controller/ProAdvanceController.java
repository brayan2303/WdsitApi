/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.controller;

import net.woden.wdsit.entity.ProAdvanceEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.ProAdvanceService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author b.algecira
 */
@RestController
@RequestMapping("proAdvance/")
public class ProAdvanceController {
    
      @Autowired
    private ProAdvanceService proAdvanceS; 

    @PostMapping(value="create")
    public ResponseEntity create(@RequestBody ProAdvanceEntity b){
        ResponseModel response=this.proAdvanceS.create(b); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="update")
    public ResponseEntity update(@RequestBody ProAdvanceEntity b){
        ResponseModel response=this.proAdvanceS.update(b); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{itemId}")
    public ResponseEntity delete(@PathVariable int itemId){
        ResponseModel response=this.proAdvanceS.delete(itemId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list/{advanceId}")
    public ResponseEntity list(@PathVariable int advanceId){
        ResponseModel response=this.proAdvanceS.list(advanceId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PostMapping(value="loadFile/{activityId}/{advanceId}")
    public ResponseEntity loadFile(@PathVariable int activityId,@PathVariable int advanceId,@RequestParam("file") MultipartFile file){
        ResponseModel response=this.proAdvanceS.loadFile(activityId,advanceId,file); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="listFile/{activityId}")
    public ResponseEntity listFile(@PathVariable int activityId){
        ResponseModel response=this.proAdvanceS.listFile(activityId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="deleteFile/{activityId}/{advanceId}/{fileName}")
    public ResponseEntity deleteFile(@PathVariable int activityId,@PathVariable int advanceId,@PathVariable String fileName){
        ResponseModel response=this.proAdvanceS.deleteFile(activityId,advanceId,fileName); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="deleteFileItem/{activityId}/{advanceId}")
    public ResponseEntity deleteFileItem(@PathVariable int activityId,@PathVariable int advanceId){
        ResponseModel response=this.proAdvanceS.deleteFileItem(activityId,advanceId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="deleteFileActivity/{activityId}")
    public ResponseEntity deleteFileActivity(@PathVariable int activityId){
        ResponseModel response=this.proAdvanceS.deleteFileActivity(activityId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
