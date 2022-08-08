package net.woden.wdsit.controller;

import net.woden.wdsit.entity.RepReportEntity;
import net.woden.wdsit.entity.RepReportPersonEntity;
import net.woden.wdsit.model.RepReportWModel;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.RepReportService;
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
@RequestMapping("repReport/")
public class RepReportController {
 
    @Autowired
    private RepReportService repReportS; 

    @PostMapping(value="create")
    public ResponseEntity create(@RequestBody RepReportEntity r){
        ResponseModel response=this.repReportS.create(r); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="update")
    public ResponseEntity update(@RequestBody RepReportEntity r){
        ResponseModel response=this.repReportS.update(r); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{reportId}")
    public ResponseEntity delete(@PathVariable int reportId){
        ResponseModel response=this.repReportS.delete(reportId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PostMapping(value="add")
    public ResponseEntity add(@RequestBody RepReportPersonEntity r){
        ResponseModel response=this.repReportS.add(r); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="remove/{personId}/{reportId}")
    public ResponseEntity remove(@PathVariable int personId,@PathVariable int reportId){
        ResponseModel response=this.repReportS.remove(personId,reportId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list")
    public ResponseEntity list(){
        ResponseModel response=this.repReportS.list(); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findAll/{personId}")
    public ResponseEntity findAll(@PathVariable int personId){
        ResponseModel response=this.repReportS.findAll(personId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findById/{reportId}")
    public ResponseEntity findById(@PathVariable int reportId){
        ResponseModel response=this.repReportS.findById(reportId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findByPersonId/{personId}/{countryId}")
    public ResponseEntity findByPersonId(@PathVariable int personId, @PathVariable int countryId){
        ResponseModel response=this.repReportS.findByPersonId(personId, countryId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value="execute/{type}/{reportId}/{storeProcedure}/{countryId}/{customerId}/{plantId}")
    public ResponseEntity execute(@PathVariable String type,@PathVariable int reportId,@PathVariable String storeProcedure,@PathVariable int countryId,@PathVariable int customerId,@PathVariable int plantId,@RequestBody RepReportWModel r){
        ResponseModel response=this.repReportS.execute(type,reportId,storeProcedure,countryId,customerId,plantId,r); 

        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PostMapping(value="executeByReportName/{reportName}/{customer}/{meta}")
    public ResponseEntity executeByReportName(@PathVariable String reportName,@PathVariable String customer,@PathVariable int meta){
        ResponseModel response=this.repReportS.executeByReportName(reportName,customer,meta); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
