 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.controller;

import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.DownloadFilesService;
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
@RequestMapping("DownloadFiles/")
public class DownloadFilesController {
    
    @Autowired
    private DownloadFilesService DownloadFiles;
    
    @GetMapping(value = "listFileClaro/")
    public ResponseEntity listFileClaro() {
        ResponseModel response = this.DownloadFiles.listFileClaro();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value = "listFileClaroSmart/")
    public ResponseEntity listFileClaroSmart() {
        ResponseModel response = this.DownloadFiles.listFileClaroSmart();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @GetMapping(value = "listFileClaroFont/")
    public ResponseEntity listFileClaroFont() {
        ResponseModel response = this.DownloadFiles.listFileClaroFont();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
     @GetMapping(value = "listFileDirectv/")
    public ResponseEntity listFileDirectv() {
        ResponseModel response = this.DownloadFiles.listFileDirectv();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
     @GetMapping(value = "listFileDirectvSmart/")
    public ResponseEntity listFileDirectvSmart() {
        ResponseModel response = this.DownloadFiles.listFileDirectvSmart();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
     @GetMapping(value = "listFileEtb/")
    public ResponseEntity listFileEtb() {
        ResponseModel response = this.DownloadFiles.listFileEtb();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
     @GetMapping(value = "listFileTigo/")
    public ResponseEntity listFileTigo() {
        ResponseModel response = this.DownloadFiles.listFileTigo();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
     @GetMapping(value = "listFileHughes/")
    public ResponseEntity listFileHughes() {
        ResponseModel response = this.DownloadFiles.listFileHughes();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
     @GetMapping(value = "listFileTigoMedellin/")
    public ResponseEntity listFileTigoMedellin() {
        ResponseModel response = this.DownloadFiles.listFileTigoMedellin();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
     @GetMapping(value = "listFileRedExterna/")
    public ResponseEntity listFileRedExterna() {
        ResponseModel response = this.DownloadFiles.listFileRedExterna();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
     @GetMapping(value = "listPlataforma/")
    public ResponseEntity listPlataforma() {
        ResponseModel response = this.DownloadFiles.listPlataforma();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
       @GetMapping(value = "findCustomerByPersonIdList/{personId}")
    public ResponseEntity findCustomerByPersonIdList(@PathVariable int personId) {
        ResponseModel response = this.DownloadFiles.findCustomerByPersonIdList(personId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
