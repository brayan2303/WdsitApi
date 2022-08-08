package net.woden.wdsit.controller;

import net.woden.wdsit.entity.RepFieldEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.RepFieldService;
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
@RequestMapping("repField/")
public class RepFieldController {
 
    @Autowired
    private RepFieldService repFieldS; 

    @PostMapping(value="create")
    public ResponseEntity create(@RequestBody RepFieldEntity r){
        ResponseModel response=this.repFieldS.create(r); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="update")
    public ResponseEntity update(@RequestBody RepFieldEntity r){
        ResponseModel response=this.repFieldS.update(r); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{fieldId}")
    public ResponseEntity delete(@PathVariable int fieldId){
        ResponseModel response=this.repFieldS.delete(fieldId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findByReportId/{reportId}")
    public ResponseEntity findByReportId(@PathVariable int reportId){
        ResponseModel response=this.repFieldS.findByReportId(reportId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findByReportName/{reportName}")
    public ResponseEntity findByReportName(@PathVariable String reportName){
        ResponseModel response=this.repFieldS.findByReportName(reportName); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list/{reportId}")
    public ResponseEntity list(@PathVariable int reportId){
        ResponseModel response=this.repFieldS.list(reportId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
