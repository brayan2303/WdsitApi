package net.woden.wdsit.controller;

import net.woden.wdsit.entity.DisDailyOperationEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.DisDailyOperationService;
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
@RequestMapping("disDailyOperation/")
public class DisDailyOperationController {
 
    @Autowired
    private DisDailyOperationService disDailyOperationS; 

    @PostMapping(value="create/{day}")
    public ResponseEntity create(@PathVariable int day,@RequestBody DisDailyOperationEntity d){
        ResponseModel response=this.disDailyOperationS.create(day,d); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="update/{id}/{goal}/{type}")
    public ResponseEntity update(@PathVariable int id,@PathVariable int goal,@PathVariable String type){
        ResponseModel response=this.disDailyOperationS.update(id,goal,type); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list/{countryId}/{year}/{monthId}/{day}")
    public ResponseEntity list(@PathVariable int countryId,@PathVariable int year,@PathVariable int monthId,@PathVariable int day){
        ResponseModel response=this.disDailyOperationS.list(countryId,year,monthId,day); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
     @GetMapping(value="codigoFamiliaList/{customer}/{contryId}")
    public ResponseEntity codigoFamiliaList(@PathVariable String customer, @PathVariable int contryId){
        ResponseModel response=this.disDailyOperationS.codigoFamiliaList(customer, contryId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
