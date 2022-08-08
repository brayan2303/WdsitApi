package net.woden.wdsit.controller;

import net.woden.wdsit.entity.BscAnalysisEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.BscAnalysisService;
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
@RequestMapping("bscAnalysis/")
public class BscAnalysisController {
 
    @Autowired
    private BscAnalysisService bscAnalysisS; 

    @PostMapping(value="create")
    public ResponseEntity create(@RequestBody BscAnalysisEntity b){
        ResponseModel response=this.bscAnalysisS.create(b); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="update")
    public ResponseEntity update(@RequestBody BscAnalysisEntity b){
        ResponseModel response=this.bscAnalysisS.update(b); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{analysisId}")
    public ResponseEntity delete(@PathVariable int analysisId){
        ResponseModel response=this.bscAnalysisS.delete(analysisId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list/{measurementDetailId}")
    public ResponseEntity list(@PathVariable int measurementDetailId){
        ResponseModel response=this.bscAnalysisS.list(measurementDetailId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="listMonth/{measurementId}/{monthId}")
    public ResponseEntity listMonth(@PathVariable int measurementId,@PathVariable int monthId){
        ResponseModel response=this.bscAnalysisS.listMonth(measurementId,monthId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findById/{analysisId}")
    public ResponseEntity findById(@PathVariable int analysisId){
        ResponseModel response=this.bscAnalysisS.findById(analysisId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
