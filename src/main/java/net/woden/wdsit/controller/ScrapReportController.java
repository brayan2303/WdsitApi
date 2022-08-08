/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.controller;


import net.sf.jasperreports.engine.JRException;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.ScrapReportService;
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
@RequestMapping("ScrapReportS/")
public class ScrapReportController {
    
     @Autowired
    private ScrapReportService ScrapReportS; 
    
    @GetMapping(value="generateReport/{id}")
    public ResponseEntity generateReport(@PathVariable int id) throws JRException {
        ResponseModel response=this.ScrapReportS.generateReport(id); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
