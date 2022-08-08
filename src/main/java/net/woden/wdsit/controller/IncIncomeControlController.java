package net.woden.wdsit.controller;

import net.woden.wdsit.entity.IncIncomeControlEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.IncIncomeControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("incIncomeControl/")
public class IncIncomeControlController {
 
    @Autowired
    private IncIncomeControlService incIncomeControlS; 

    @PostMapping(value="create")
    public ResponseEntity create(@RequestBody IncIncomeControlEntity i){
        ResponseModel response=this.incIncomeControlS.create(i); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findLast/{personId}")
    public ResponseEntity findLast(@PathVariable int personId){
        ResponseModel response=this.incIncomeControlS.findLast(personId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="update")
    public ResponseEntity update(@RequestParam int personId){
        ResponseModel response=this.incIncomeControlS.update(personId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
