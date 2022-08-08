package net.woden.wdsit.controller;

import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.PriUnreadableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("priUnreadable/")
public class PriUnreadableController {
 
    @Autowired
    private PriUnreadableService priUnreadableS; 

    @PostMapping(value="create/{countryId}/{customerId}/{creationUserId}/{quantity}")
    public ResponseEntity create(@PathVariable int countryId,@PathVariable int customerId,@PathVariable int creationUserId,@PathVariable int quantity){
        ResponseModel response=this.priUnreadableS.create(countryId,customerId,creationUserId,quantity); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list/{countryId}/{customerId}/{creationUserId}/{quantity}")
    public ResponseEntity list(@PathVariable int countryId,@PathVariable int customerId,@PathVariable int creationUserId,@PathVariable int quantity){
        ResponseModel response=this.priUnreadableS.list(countryId,customerId,creationUserId,quantity); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
