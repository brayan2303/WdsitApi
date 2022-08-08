package net.woden.wdsit.controller;

import net.woden.wdsit.entity.PqrRegionalEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.PqrRegionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pqrRegional/")
public class PqrRegionalController {
 
    @Autowired
    private PqrRegionalService pqrRegionalS; 

    @PostMapping(value="create/{countryId}")
    public ResponseEntity create(@RequestBody PqrRegionalEntity p, @PathVariable int countryId){
        ResponseModel response=this.pqrRegionalS.create(p, countryId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="update")
    public ResponseEntity update(@RequestBody PqrRegionalEntity p){
        ResponseModel response=this.pqrRegionalS.update(p); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findAll/{cityId}/{countryId}")
    public ResponseEntity findAll(@PathVariable int cityId, @PathVariable int countryId){
        ResponseModel response=this.pqrRegionalS.findAll(cityId,countryId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list/{cityId}/{countryId}")
    public ResponseEntity list(@PathVariable int cityId,  @PathVariable int countryId){
        ResponseModel response=this.pqrRegionalS.list(cityId, countryId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
