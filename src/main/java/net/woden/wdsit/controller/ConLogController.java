package net.woden.wdsit.controller;

import net.woden.wdsit.entity.ConLogEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.ConLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("conLog/")
public class ConLogController {
 
    @Autowired
    private ConLogService conLogS; 

    @PostMapping(value="create")
    public ResponseEntity create(@RequestBody ConLogEntity c){
        ResponseModel response=this.conLogS.create(c); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="viewDay")
    public ResponseEntity viewDay(){
        ResponseModel response=this.conLogS.viewDay(); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="viewControlPanel")
    public ResponseEntity viewControlPanel(){
        ResponseModel response=this.conLogS.viewControlPanel(); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
