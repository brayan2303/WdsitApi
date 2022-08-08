package net.woden.wdsit.controller;

import net.woden.wdsit.entity.DisAssistenceEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.DisAssistenceService;
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
@RequestMapping("disAssistence/")
public class DisAssistenceController {
 
    @Autowired
    private DisAssistenceService disAssistenceS; 

    @PostMapping(value="create")
    public ResponseEntity create(@RequestBody DisAssistenceEntity d){
        ResponseModel response=this.disAssistenceS.create(d); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{monthId}/{headCountId}/{day}")
    public ResponseEntity delete(@PathVariable int monthId,@PathVariable int headCountId,@PathVariable int day){
        ResponseModel response=this.disAssistenceS.delete(monthId,headCountId,day); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list/{monthId}")
    public ResponseEntity list(@PathVariable int monthId){
        ResponseModel response=this.disAssistenceS.list(monthId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
