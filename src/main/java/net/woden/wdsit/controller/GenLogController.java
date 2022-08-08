package net.woden.wdsit.controller;

import net.woden.wdsit.entity.GenLogEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.GenLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("genLog/")
public class GenLogController {
 
    @Autowired
    private GenLogService genLogS; 

    @PostMapping(value="create")
    public ResponseEntity create(@RequestBody GenLogEntity g){
        ResponseModel response=this.genLogS.create(g); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="loginDay")
    public ResponseEntity loginDay(){
        ResponseModel response=this.genLogS.loginDay(); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="loginPerson")
    public ResponseEntity loginPerson(){
        ResponseModel response=this.genLogS.loginPerson(); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
