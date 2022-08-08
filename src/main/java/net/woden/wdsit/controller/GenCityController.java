package net.woden.wdsit.controller;

import net.woden.wdsit.entity.GenCityEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.GenCityService;
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
@RequestMapping("genCity/")
public class GenCityController {
 
    @Autowired
    private GenCityService genCityS; 

    @PostMapping(value="create")
    public ResponseEntity create(@RequestBody GenCityEntity g){
        ResponseModel response=this.genCityS.create(g); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="update")
    public ResponseEntity update(@RequestBody GenCityEntity g){
        ResponseModel response=this.genCityS.update(g); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{cityId}")
    public ResponseEntity delete(@PathVariable int cityId){
        ResponseModel response=this.genCityS.delete(cityId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="listActive/{departmentId}")
    public ResponseEntity listActive(@PathVariable int departmentId){
        ResponseModel response=this.genCityS.listActive(departmentId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @GetMapping(value="findById/{cityId}")
    public ResponseEntity findById(@PathVariable int cityId){
        ResponseModel response=this.genCityS.findById(cityId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @GetMapping(value="findByCountryId/{countryId}")
    public ResponseEntity findByCountryId(@PathVariable int countryId){
        ResponseModel response=this.genCityS.findByCountryId(countryId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @GetMapping(value="list")
    public ResponseEntity list(){
        ResponseModel response=this.genCityS.list(); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
