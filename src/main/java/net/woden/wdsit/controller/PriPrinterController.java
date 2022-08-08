package net.woden.wdsit.controller;

import net.woden.wdsit.entity.PriPrinterEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.PriPrinterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("priPrinter/")
public class PriPrinterController {
 
    @Autowired
    private PriPrinterService priPrinterS; 

    @PostMapping(value="create")
    public ResponseEntity create(@RequestBody PriPrinterEntity p){
        ResponseModel response=this.priPrinterS.create(p); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="update")
    public ResponseEntity update(@RequestBody PriPrinterEntity p){
        ResponseModel response=this.priPrinterS.update(p); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{id}")
    public ResponseEntity delete(@PathVariable int id){
        ResponseModel response=this.priPrinterS.delete(id); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findById/{id}")
    public ResponseEntity findById(@PathVariable int id){
        ResponseModel response=this.priPrinterS.findById(id); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list")
    public ResponseEntity list(){
        ResponseModel response=this.priPrinterS.list(); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="listActive/{customerId}/{location}")
    public ResponseEntity list(@PathVariable int customerId,@PathVariable String location){
        ResponseModel response=this.priPrinterS.listActive(customerId,location); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
