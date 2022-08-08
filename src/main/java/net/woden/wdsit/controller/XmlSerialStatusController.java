/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.controller;

import java.sql.SQLException;
import net.woden.wdsit.model.XmlSerialStatusModel;
import net.woden.wdsit.service.XmlSerialStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author b.algecira
 */
@RestController
@RequestMapping("XmlSerial/")
public class XmlSerialStatusController {
    
    @Autowired
    private XmlSerialStatusService XmlSerialS;
    
    @GetMapping(value = "xmlSerialEntry/{serial}")
    public @ResponseBody XmlSerialStatusModel xmlSerialEntry(@PathVariable String serial){
        XmlSerialStatusModel response = this.XmlSerialS.xmlSerialEntry(serial);
        return response;
    }
    @GetMapping(value = "xmlSerialProcces/{serial}")
    public @ResponseBody XmlSerialStatusModel xmlSerialProcces(@PathVariable String serial){
        XmlSerialStatusModel response=this.XmlSerialS.xmlSerialProcces(serial);
        return response;
    }
    @GetMapping(value = "xmlSerialDispatch/{serial}")
    public @ResponseBody XmlSerialStatusModel xmlSerialDispatch(@PathVariable String serial){
        XmlSerialStatusModel response=this.XmlSerialS.xmlSerialDispatch(serial);
        return response;
    }
    @GetMapping(value = "xmlPrueba/{serial}")
    public @ResponseBody XmlSerialStatusModel xmlPrueba(@PathVariable String serial){
        XmlSerialStatusModel response=this.XmlSerialS.xmlPrueba(serial);
        return response;
    }
    
     @GetMapping(value = "xmlPrueba2/{serial}")
    public @ResponseBody XmlSerialStatusModel xmlPrueba2(@PathVariable String serial){
         XmlSerialStatusModel response=this.XmlSerialS.xmlPrueba2(serial);
        return response;
    }
   
}
