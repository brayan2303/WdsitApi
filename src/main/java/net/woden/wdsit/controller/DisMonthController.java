package net.woden.wdsit.controller;

import net.woden.wdsit.entity.DisMonthEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.DisMonthService;
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
@RequestMapping("disMonth/")
public class DisMonthController {
 
    @Autowired
    private DisMonthService disMonthS; 

    @PostMapping(value="create")
    public ResponseEntity create(@RequestBody DisMonthEntity d){
        ResponseModel response=this.disMonthS.create(d); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="update")
    public ResponseEntity update(@RequestBody DisMonthEntity d){
        ResponseModel response=this.disMonthS.update(d); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{monthId}")
    public ResponseEntity delete(@PathVariable int monthId){
        ResponseModel response=this.disMonthS.delete(monthId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list")
    public ResponseEntity list(){
        ResponseModel response=this.disMonthS.list(); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="listAll/{year}")
    public ResponseEntity listAll(@PathVariable int year){
        ResponseModel response=this.disMonthS.listAll(year); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
