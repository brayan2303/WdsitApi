package net.woden.wdsit.controller;

import net.woden.wdsit.entity.RepLogEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.RepLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("repLog/")
public class RepLogController {
 
    @Autowired
    private RepLogService repLogS; 

    @PostMapping(value="create")
    public ResponseEntity create(@RequestBody RepLogEntity r){
        ResponseModel response=this.repLogS.create(r); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="downloadDay")
    public ResponseEntity downloadDay(){
        ResponseModel response=this.repLogS.downloadDay(); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="downloadReport")
    public ResponseEntity downloadReport(){
        ResponseModel response=this.repLogS.downloadReport(); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
