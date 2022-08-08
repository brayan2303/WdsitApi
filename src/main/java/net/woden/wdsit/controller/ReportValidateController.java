package net.woden.wdsit.controller;

import net.woden.wdsit.entity.LoadValidateReportEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.ReportValidateService;
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
 * @author f.casallas
 */
@RestController
@RequestMapping("LoadValidateReport/")
public class ReportValidateController {

    @Autowired
    private ReportValidateService ValidateReport;

    @PostMapping(value = "create")
    public ResponseEntity create(@RequestBody LoadValidateReportEntity l) {
        ResponseModel response = this.ValidateReport.create(l);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping(value = "update") //servicio actualizar
    public ResponseEntity update(@RequestBody LoadValidateReportEntity l) {
        ResponseModel response = this.ValidateReport.update(l);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "ListCountry/{userId}")
    public ResponseEntity FindCountry(@PathVariable int userId) {
        ResponseModel response = this.ValidateReport.ListCountry(userId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "listColumns/{countryId}")
    public ResponseEntity listColumns(@PathVariable int countryId) {
        ResponseModel response = this.ValidateReport.listColumns(countryId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
