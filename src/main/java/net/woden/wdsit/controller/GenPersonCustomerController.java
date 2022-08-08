package net.woden.wdsit.controller;

import net.woden.wdsit.entity.GenPersonCustomerEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.GenPersonCustomerService;
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
@RequestMapping("genPersonCustomer/")
public class GenPersonCustomerController {
 
    @Autowired
    private GenPersonCustomerService genPersonCustomerS; 

    @PostMapping(value="create")
    public ResponseEntity create(@RequestBody GenPersonCustomerEntity g){
        ResponseModel response=this.genPersonCustomerS.create(g); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{personId}/{customerId}")
    public ResponseEntity delete(@PathVariable int personId,@PathVariable int customerId){
        ResponseModel response=this.genPersonCustomerS.delete(personId,customerId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list/{personId}/{countryId}")
    public ResponseEntity list(@PathVariable int personId,@PathVariable int countryId){
        ResponseModel response=this.genPersonCustomerS.list(personId,countryId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="listCustomer/{personId}/{countryId}")
    public ResponseEntity listCustomer(@PathVariable int personId,@PathVariable int countryId){
        ResponseModel response=this.genPersonCustomerS.listCustomer(personId,countryId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
