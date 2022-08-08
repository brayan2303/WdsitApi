package net.woden.wdsit.controller;

import net.woden.wdsit.entity.DisMonthDayEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.DisMonthDayService;
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
@RequestMapping("disMonthDay/")
public class DisMonthDayController {
 
    @Autowired
    private DisMonthDayService disMonthDayS; 

    @PostMapping(value="create")
    public ResponseEntity create(@RequestBody DisMonthDayEntity d){
        ResponseModel response=this.disMonthDayS.create(d); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{monthId}/{day}")
    public ResponseEntity delete(@PathVariable int monthId,@PathVariable int day){
        ResponseModel response=this.disMonthDayS.delete(monthId,day); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list/{monthId}/{year}/{month}")
    public ResponseEntity list(@PathVariable int monthId,@PathVariable int year,@PathVariable int month){
        ResponseModel response=this.disMonthDayS.list(monthId,year,month); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="days/{monthId}/{year}/{month}")
    public ResponseEntity days(@PathVariable int monthId,@PathVariable int year,@PathVariable String month){
        ResponseModel response=this.disMonthDayS.days(monthId,year,month); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
