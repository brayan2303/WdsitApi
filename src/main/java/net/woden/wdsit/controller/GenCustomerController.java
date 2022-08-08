package net.woden.wdsit.controller;

import net.woden.wdsit.entity.GenCustomerEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.GenCustomerService;
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
@RequestMapping("genCustomer/")
public class GenCustomerController {
 
    @Autowired
    private GenCustomerService genCustomerS; 

    @PostMapping(value="create")
    public ResponseEntity create(@RequestBody GenCustomerEntity g){
        ResponseModel response=this.genCustomerS.create(g);
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="update")
    public ResponseEntity update(@RequestBody GenCustomerEntity g){
        ResponseModel response=this.genCustomerS.update(g);
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{customerId}")
    public ResponseEntity delete(@PathVariable int customerId){
        ResponseModel response=this.genCustomerS.delete(customerId);
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findAll")
    public ResponseEntity findAll(){
        ResponseModel response=this.genCustomerS.findAll(); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findById/{customerId}")
    public ResponseEntity findById(@PathVariable int customerId){
        ResponseModel response=this.genCustomerS.findById(customerId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findByIncomeActive")
    public ResponseEntity findByIncomeActive(){
        ResponseModel response=this.genCustomerS.findByIncomeActive(); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list")
    public ResponseEntity list(){
        ResponseModel response=this.genCustomerS.list(); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
     @GetMapping(value="findByCustomer/{customer}")
    public ResponseEntity findByCustomer(@PathVariable String customerId){
        ResponseModel response=this.genCustomerS.findByCustomer(customerId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
