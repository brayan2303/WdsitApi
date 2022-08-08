package net.woden.wdsit.controller;

import net.woden.wdsit.entity.InvCoutingAssistantEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.InvCoutingAssistantService;
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
@RequestMapping("invCoutingAssistant/")
public class InvCoutingAssistantController {
 
    @Autowired
    private InvCoutingAssistantService invCoutingAssistantS; 

    @PostMapping(value="create")
    public ResponseEntity create(@RequestBody InvCoutingAssistantEntity i){
        ResponseModel response=this.invCoutingAssistantS.create(i); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{coutingId}/{personId}")
    public ResponseEntity delete(@PathVariable int coutingId,@PathVariable int personId){
        ResponseModel response=this.invCoutingAssistantS.delete(coutingId,personId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findAll/{coutingId}")
    public ResponseEntity findAll(@PathVariable int coutingId){
        ResponseModel response=this.invCoutingAssistantS.findAll(coutingId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
