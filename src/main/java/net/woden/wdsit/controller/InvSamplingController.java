package net.woden.wdsit.controller;

import net.woden.wdsit.entity.InvSamplingEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.InvSamplingService;
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
@RequestMapping("invSampling/")
public class InvSamplingController {
 
    @Autowired
    private InvSamplingService invSamplingS; 

    @PostMapping(value="create")
    public ResponseEntity create(@RequestBody InvSamplingEntity i){
        ResponseModel response=this.invSamplingS.create(i); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{cyclicId}/{value}")
    public ResponseEntity delete(@PathVariable int cyclicId,@PathVariable String value){
        ResponseModel response=this.invSamplingS.delete(cyclicId,value); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findAll/{cyclicId}/{system}/{typeSampling}/{type}/{customer}")
    public ResponseEntity findAll(@PathVariable int cyclicId,@PathVariable String system,@PathVariable String typeSampling,@PathVariable String type,@PathVariable String customer){
        ResponseModel response=this.invSamplingS.findAll(cyclicId,system,typeSampling,type,customer); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
