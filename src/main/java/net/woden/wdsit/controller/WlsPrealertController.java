package net.woden.wdsit.controller;

import net.woden.wdsit.entity.WlsPrealertEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.WlsPrealertService;
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

@RestController
@RequestMapping("wlsPrealert/")
public class WlsPrealertController {
    @Autowired
    private WlsPrealertService wlsPrealertS;
    
    @PostMapping("create")
    public ResponseEntity create(@RequestBody WlsPrealertEntity w){
        ResponseModel response=this.wlsPrealertS.create(w);
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity delete(@PathVariable int id){
        ResponseModel response=this.wlsPrealertS.delete(id);
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping("list/{proyectId}")
    public ResponseEntity list(@PathVariable int proyectId){
        ResponseModel response=this.wlsPrealertS.list(proyectId);
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
