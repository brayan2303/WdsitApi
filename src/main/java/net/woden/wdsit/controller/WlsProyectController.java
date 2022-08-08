package net.woden.wdsit.controller;

import net.woden.wdsit.entity.WlsProyectEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.WlsProyectService;
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
@RequestMapping("wlsProyect/")
public class WlsProyectController {
 
    @Autowired
    private WlsProyectService wlsProyectS; 

    @PostMapping(value="create")
    public ResponseEntity create(@RequestBody WlsProyectEntity w){
        ResponseModel response=this.wlsProyectS.create(w); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="update")
    public ResponseEntity update(@RequestBody WlsProyectEntity w){
        ResponseModel response=this.wlsProyectS.update(w); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{id}")
    public ResponseEntity delete(@PathVariable int id){
        ResponseModel response=this.wlsProyectS.delete(id); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list")
    public ResponseEntity list(){
        ResponseModel response=this.wlsProyectS.list(); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="listByCustomer/{countryId}/{customerId}")
    public ResponseEntity listByCustomer(@PathVariable int countryId,@PathVariable int customerId){
        ResponseModel response=this.wlsProyectS.listByCustomer(countryId,customerId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findById/{id}")
    public ResponseEntity findById(@PathVariable int id){
        ResponseModel response=this.wlsProyectS.findById(id); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
