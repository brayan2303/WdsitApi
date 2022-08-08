package net.woden.wdsit.controller;

import net.woden.wdsit.entity.GenPersonCountryEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.GenPersonCountryService;
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
@RequestMapping("genPersonCountry/")
public class GenPersonCountryController {
 
    @Autowired
    private GenPersonCountryService genPersonCountryS; 

    @PostMapping(value="create")
    public ResponseEntity create(@RequestBody GenPersonCountryEntity g){
        ResponseModel response=this.genPersonCountryS.create(g); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{personId}/{countryId}")
    public ResponseEntity delete(@PathVariable int personId,@PathVariable int countryId){
        ResponseModel response=this.genPersonCountryS.delete(personId,countryId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list/{personId}")
    public ResponseEntity list(@PathVariable int personId){
        ResponseModel response=this.genPersonCountryS.list(personId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="listCountry/{personId}")
    public ResponseEntity listCountry(@PathVariable int personId){
        ResponseModel response=this.genPersonCountryS.listCountry(personId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
