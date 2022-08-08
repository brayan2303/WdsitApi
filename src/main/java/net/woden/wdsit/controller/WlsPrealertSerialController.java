package net.woden.wdsit.controller;

import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.WlsPrealertSerialService;
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
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("wlsPrealertSerial/")
public class WlsPrealertSerialController {
    @Autowired
    private WlsPrealertSerialService wlsPrealertSerialS;
    
    @PostMapping("create/{customer}/{prealertId}")
    public ResponseEntity create(@PathVariable String customer,@PathVariable int prealertId,@RequestBody MultipartFile file){
        ResponseModel response=this.wlsPrealertSerialS.create(customer,prealertId,file);
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping("delete/{prealertId}")
    public ResponseEntity delete(@PathVariable int prealertId){
        ResponseModel response=this.wlsPrealertSerialS.delete(prealertId);
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping("list/{prealertId}")
    public ResponseEntity list(@PathVariable int prealertId){
        ResponseModel response=this.wlsPrealertSerialS.list(prealertId);
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
