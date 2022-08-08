package net.woden.wdsit.controller;

import net.woden.wdsit.entity.RepFilterEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.RepFilterService;
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
@RequestMapping("repFilter/")
public class RepFilterController {
 
    @Autowired
    private RepFilterService repFilterS; 

    @PostMapping(value="create")
    public ResponseEntity create(@RequestBody RepFilterEntity r){
        ResponseModel response=this.repFilterS.create(r); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="update")
    public ResponseEntity update(@RequestBody RepFilterEntity r){
        ResponseModel response=this.repFilterS.update(r); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{filterId}")
    public ResponseEntity delete(@PathVariable int filterId){
        ResponseModel response=this.repFilterS.delete(filterId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findByReportId/{reportId}")
    public ResponseEntity findByReportId(@PathVariable int reportId){
        ResponseModel response=this.repFilterS.findByReportId(reportId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list/{reportId}")
    public ResponseEntity list(@PathVariable int reportId){
        ResponseModel response=this.repFilterS.list(reportId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findQuery/{query}")
    public ResponseEntity findQuery(@PathVariable String query){
        ResponseModel response=this.repFilterS.findQuery(query); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
