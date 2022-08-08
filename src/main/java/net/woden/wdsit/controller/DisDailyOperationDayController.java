package net.woden.wdsit.controller;

import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.DisDailyOperationDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("disDailyOperationDay/")
public class DisDailyOperationDayController {
 
    @Autowired
    private DisDailyOperationDayService disDailyOperationDayS; 

    @PostMapping(value="create/{dailyOperationId}/{day}/{type}/{value}")
    public ResponseEntity create(@PathVariable int dailyOperationId,@PathVariable int day,@PathVariable String type,@PathVariable int value){
        ResponseModel response=this.disDailyOperationDayS.create(dailyOperationId,day,type,value); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
