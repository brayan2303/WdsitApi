/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.controller;

import net.woden.wdsit.entity.PqrUserProjectEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.PqrUserProjectService;
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
 * @author b.algecira
 */
@RestController
@RequestMapping("PqrUserProjec/")
public class PqrUserProjectController {
    
    @Autowired
    private PqrUserProjectService PqrUserProjec;
    
        @PostMapping(value = "create") // servicio crear y almacenar
    public ResponseEntity create(@RequestBody PqrUserProjectEntity p) {
        ResponseModel response = this.PqrUserProjec.create(p);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping(value = "delete/{userId}/{projectdId}")
    public ResponseEntity delete(@PathVariable int userId, @PathVariable int projectdId) {
        ResponseModel response = this.PqrUserProjec.delete(userId, projectdId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value = "list") //servicio mostrar en pantalla
    public ResponseEntity list() {
        ResponseModel response = this.PqrUserProjec.list();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
     @GetMapping(value="findAll/{userId}")
    public ResponseEntity findAll(@PathVariable int userId){
        ResponseModel response=this.PqrUserProjec.findAll(userId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
