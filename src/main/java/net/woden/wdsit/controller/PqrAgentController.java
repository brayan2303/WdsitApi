package net.woden.wdsit.controller;

import net.woden.wdsit.entity.GenPersonEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.PqrAgentService;
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
@RequestMapping("pqrAgent/")
public class PqrAgentController {
 
    @Autowired
    private PqrAgentService pqrAgentS; 

    @PostMapping(value="create/{countryId}")
    public ResponseEntity create(@RequestBody GenPersonEntity g, @PathVariable int countryId){
        ResponseModel response=this.pqrAgentS.create(g, countryId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{agentId}")
    public ResponseEntity delete(@PathVariable int agentId){
        ResponseModel response=this.pqrAgentS.delete(agentId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list/{countryId}")
    public ResponseEntity list(@PathVariable int countryId){
        ResponseModel response=this.pqrAgentS.list(countryId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
