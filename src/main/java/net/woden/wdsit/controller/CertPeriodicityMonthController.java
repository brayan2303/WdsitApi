/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.woden.wdsit.controller;

import net.woden.wdsit.entity.CertCertPeriodicityEntity;
import net.woden.wdsit.entity.CertPeriodicityMonthEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.CertCertPeriodicityService;
import net.woden.wdsit.service.CertPeriodicityMonthService;
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
@RequestMapping("certPeriodicityMonth/")
public class CertPeriodicityMonthController {
   
    @Autowired
    private CertPeriodicityMonthService CertPeriodicityMonth;

    @PostMapping(value = "create") // servicio crear y almacenar
    public ResponseEntity create(@RequestBody CertPeriodicityMonthEntity a) {
        ResponseModel response = this.CertPeriodicityMonth.create(a);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping(value = "delete/{periodicityId}/{monthId}")
    public ResponseEntity delete(@PathVariable int periodicityId, @PathVariable int monthId) {
        ResponseModel response = this.CertPeriodicityMonth.delete(periodicityId, monthId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value = "list") //servicio mostrar en pantalla
    public ResponseEntity list() {
        ResponseModel response = this.CertPeriodicityMonth.list();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
