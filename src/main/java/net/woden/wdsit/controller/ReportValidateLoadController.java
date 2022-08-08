package net.woden.wdsit.controller;

import java.io.IOException;
import java.sql.SQLException;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.ReportValidateLoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author f.casallas
 */
@RestController
@RequestMapping("ReportValidateLoad/")
public class ReportValidateLoadController {

    @Autowired
    private ReportValidateLoadService ReportValLoad;

    @PostMapping(value = "loadFile/{idCountry}/{userId}/{typeFiles}")
    public ResponseEntity loadFile(@PathVariable int idCountry, @PathVariable int userId, @PathVariable String typeFiles, @RequestParam("files") MultipartFile[] files) {
        ResponseModel response = this.ReportValLoad.loadFile(idCountry, userId, typeFiles, files);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "validateFile/{userId}/{idCountry}/{typeFiles}")
    public ResponseEntity validateFile(@PathVariable int userId, @PathVariable int idCountry, @PathVariable String typeFiles) {
        ResponseModel response = this.ReportValLoad.validateFile(userId, idCountry, typeFiles);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "ReportValidateFindCountry/{countryId}")
    public ResponseEntity ReportValidateFindCountry(@PathVariable int countryId) {
        ResponseModel response = this.ReportValLoad.ReportValidateFindCountry(countryId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value = "ReportValidateLoadArchive/{userId}")
    public ResponseEntity ReportValidateLoadArchive(@PathVariable int userId) throws IOException {
        ResponseModel response = this.ReportValLoad.ReportValidateLoadArchive(userId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "ReportValidateLoadDownload/{userId}/{codeLoad}")
    public ResponseEntity ReportValidateLoadDownload(@PathVariable int userId, @PathVariable String codeLoad) {
        ResponseModel response = this.ReportValLoad.ReportValidateLoadDownload(userId, codeLoad);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping(value = "ReportValidateLoadDeleteArchive/{userId}")
    public ResponseEntity ReportValidateLoadDeleteArchive(@PathVariable int userId) {
        ResponseModel response = this.ReportValLoad.ReportValidateLoadDeleteArchive(userId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "ReportValidateNameArchive/{userId}")
    public ResponseEntity ReportValidateNameArchive(@PathVariable int userId) {
        ResponseModel response = this.ReportValLoad.ReportValidateNameArchive(userId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @GetMapping(value = "listAll/{country}")
    public ResponseEntity listAll(@PathVariable String country) throws IOException {
        ResponseModel response = this.ReportValLoad.listAll(country);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
