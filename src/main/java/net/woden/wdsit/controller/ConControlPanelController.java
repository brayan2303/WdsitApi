package net.woden.wdsit.controller;

import net.woden.wdsit.entity.ConControlPanelEntity;
import net.woden.wdsit.entity.ConControlPanelPersonEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.ConControlPanelService;
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
@RequestMapping("conControlPanel/")
public class ConControlPanelController {
 
    @Autowired
    private ConControlPanelService conControlPanelS; 

    @PostMapping(value="create")
    public ResponseEntity create(@RequestBody ConControlPanelEntity c){
        ResponseModel response=this.conControlPanelS.create(c); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="update")
    public ResponseEntity update(@RequestBody ConControlPanelEntity c){
        ResponseModel response=this.conControlPanelS.update(c); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{controlPanelId}")
    public ResponseEntity delete(@PathVariable int controlPanelId){
        ResponseModel response=this.conControlPanelS.delete(controlPanelId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PostMapping(value="add")
    public ResponseEntity add(@RequestBody ConControlPanelPersonEntity c){
        ResponseModel response=this.conControlPanelS.add(c); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="remove/{personId}/{controlPanelId}")
    public ResponseEntity remove(@PathVariable int personId,@PathVariable int controlPanelId){
        ResponseModel response=this.conControlPanelS.remove(personId,controlPanelId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list")
    public ResponseEntity list(){
        ResponseModel response=this.conControlPanelS.list(); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findAll/{personId}")
    public ResponseEntity findAll(@PathVariable int personId){
        ResponseModel response=this.conControlPanelS.findAll(personId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findById/{controlPanelId}")
    public ResponseEntity findById(@PathVariable int controlPanelId){
        ResponseModel response=this.conControlPanelS.findById(controlPanelId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findByPersonId/{personId}")
    public ResponseEntity findByPersonId(@PathVariable int personId){
        ResponseModel response=this.conControlPanelS.findByPersonId(personId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
