/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.woden.wdsit.controller;

import net.woden.wdsit.entity.RepFlagLogEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.RepFlagLogService;
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
 * @author b.algecira
 */
@RestController
    @RequestMapping("RepFlagLogS/")
public class RepFlagLogController {

    @Autowired
    private RepFlagLogService RepFlagLogS;

    @PostMapping(value = "create")
    public ResponseEntity create(@RequestBody RepFlagLogEntity r) {
        ResponseModel response = this.RepFlagLogS.create(r);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping(value = "update")
    public ResponseEntity update(@RequestBody RepFlagLogEntity r) {
        ResponseModel response = this.RepFlagLogS.update(r);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="listValidation/{reportId}/{userId}")
    public ResponseEntity listValidation(@PathVariable int reportId, @PathVariable int userId){
        ResponseModel response=this.RepFlagLogS.listValidation(reportId,userId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
        @GetMapping(value="listValidationTime/{reportId}/{userId}")
    public ResponseEntity listValidationTime(@PathVariable int reportId, @PathVariable int userId){
        ResponseModel response=this.RepFlagLogS.listValidationTime(reportId,userId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
