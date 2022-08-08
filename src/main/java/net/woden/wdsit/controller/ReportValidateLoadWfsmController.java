/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.woden.wdsit.controller;

import java.util.ArrayList;
import net.woden.wdsit.model.ReportValidateLoadWfsmModel;
import net.woden.wdsit.service.ReportValidateLoadWfsmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author b.algecira
 */
@RestController
@RequestMapping("ReportValidateLoadWfsmS/")
public class ReportValidateLoadWfsmController {

    @Autowired
    private ReportValidateLoadWfsmService ReportValidateLoadWfsmS;

    @GetMapping(value = "wfsm/{abreviatura}/{country}/{customer}") //servicio mostrar en pantalla
    public ArrayList<ReportValidateLoadWfsmModel> wfsm(@PathVariable String abreviatura, @PathVariable String country, @PathVariable String customer) throws Exception {
        ArrayList<ReportValidateLoadWfsmModel> response = this.ReportValidateLoadWfsmS.wfsm( abreviatura,country, customer);
        return response;
    }
}
