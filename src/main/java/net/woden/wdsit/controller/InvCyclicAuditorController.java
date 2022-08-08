package net.woden.wdsit.controller;

import net.woden.wdsit.entity.InvCyclicAuditorEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.InvCyclicAuditorService;
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
@RequestMapping("invCyclicAuditor/")
public class InvCyclicAuditorController {
 
    @Autowired
    private InvCyclicAuditorService invCyclicAuditorS; 

    @PostMapping(value="create")
    public ResponseEntity create(@RequestBody InvCyclicAuditorEntity i){
        ResponseModel response=this.invCyclicAuditorS.create(i); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{cyclicId}/{personId}")
    public ResponseEntity delete(@PathVariable int cyclicId,@PathVariable int personId){
        ResponseModel response=this.invCyclicAuditorS.delete(cyclicId,personId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findAll/{cyclicId}")
    public ResponseEntity findAll(@PathVariable int cyclicId){
        ResponseModel response=this.invCyclicAuditorS.findAll(cyclicId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
