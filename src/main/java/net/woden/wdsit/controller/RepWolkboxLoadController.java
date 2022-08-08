/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.controller;

import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.RepWolkboxLoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author b.algecira
 */
@RestController
@RequestMapping("RepWolkboxLoadS/")
public class RepWolkboxLoadController {

    @Autowired
    private RepWolkboxLoadService RepWolkboxLoadS;
    //   @PostMapping(value = "loadFile/")
    // public ResponseEntity loadFile(@RequestParam("files") MultipartFile[] files) throws SQLException {
    // ResponseModel response = this.LoadClientPrealert.loadFile(files);
    //   return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    // }

    @PostMapping("create/{loadId}")
    public ResponseEntity create(@PathVariable int loadId, @RequestBody MultipartFile file) {
        ResponseModel response = this.RepWolkboxLoadS.create(loadId, file);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /*   @DeleteMapping("delete/{customer}")
    public ResponseEntity delete(@PathVariable int customer) {
        ResponseModel response = this.LoadClientPrealert.delete(customer);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
     */
    @GetMapping(value = "list") //servicio mostrar en pantalla
    public ResponseEntity list(){
        ResponseModel response = this.RepWolkboxLoadS.list();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
