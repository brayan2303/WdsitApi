package net.woden.wdsit.controller;

import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.InvBaseService;
import net.woden.wdsit.service.PriBaseService;
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

@RestController
@RequestMapping("priBase/")
public class PriBaseController {
 
    @Autowired
    private PriBaseService priBaseS; 

    @PostMapping(value="create/{customer}")
    public ResponseEntity create(@PathVariable String customer,@RequestParam("file") MultipartFile file){
        ResponseModel response=this.priBaseS.create(customer,file); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{customer}")
    public ResponseEntity delete(@PathVariable String customer){
        ResponseModel response=this.priBaseS.delete(customer); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findSerial/{serial}/{customer}")
    public ResponseEntity findSerial(@PathVariable String serial,@PathVariable String customer){
        ResponseModel response=this.priBaseS.findSerial(serial,customer); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list/{customer}")
    public ResponseEntity list(@PathVariable String customer){
        ResponseModel response=this.priBaseS.list(customer); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
