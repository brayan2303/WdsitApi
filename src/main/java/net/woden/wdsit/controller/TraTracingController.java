package net.woden.wdsit.controller;

import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.TraTracingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("traTracing/")
public class TraTracingController {
 
    @Autowired
    private TraTracingService traTracingS; 

    @GetMapping(value="findSystem/{countryId}/{customer}/{serial}")
    public ResponseEntity findSystem(@PathVariable int countryId,@PathVariable String customer,@PathVariable String serial){
        ResponseModel response=this.traTracingS.findSystem(countryId,customer,serial); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findFase/{countryId}/{customer}/{system}/{serial}")
    public ResponseEntity findFase(@PathVariable int countryId,@PathVariable String customer,@PathVariable String system,@PathVariable String serial){
        ResponseModel response=this.traTracingS.findFase(countryId,customer,system,serial); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findDetail/{countryId}/{system}/{customer}/{fase}/{serialId}")
    public ResponseEntity findDetail(@PathVariable int countryId,@PathVariable String system,@PathVariable String customer,@PathVariable String fase,@PathVariable int serialId){
        ResponseModel response=this.traTracingS.findDetail(countryId,system,customer,fase,serialId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findTimeline/{countryId}/{system}/{customer}/{serial}")
    public ResponseEntity findTimeline(@PathVariable int countryId,@PathVariable String system,@PathVariable String customer,@PathVariable String serial){
        ResponseModel response=this.traTracingS.findTimeline(countryId,system,customer,serial); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
