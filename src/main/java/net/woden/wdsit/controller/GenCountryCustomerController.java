package net.woden.wdsit.controller;

import net.woden.wdsit.entity.GenCountryCustomerEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.GenCountryCustomerService;
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
@RequestMapping("genCountryCustomer/")
public class GenCountryCustomerController {
 
    @Autowired
    private GenCountryCustomerService genCountryCustomerS; 

    @PostMapping(value="create")
    public ResponseEntity create(@RequestBody GenCountryCustomerEntity g){
        ResponseModel response=this.genCountryCustomerS.create(g); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{countryId}/{customerId}")
    public ResponseEntity delete(@PathVariable int countryId,@PathVariable int customerId){
        ResponseModel response=this.genCountryCustomerS.delete(countryId,customerId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list/{countryId}")
    public ResponseEntity list(@PathVariable int countryId){
        ResponseModel response=this.genCountryCustomerS.list(countryId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="listCustomer/{countryId}")
    public ResponseEntity listCustomer(@PathVariable int countryId){
        ResponseModel response=this.genCountryCustomerS.listCustomer(countryId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
