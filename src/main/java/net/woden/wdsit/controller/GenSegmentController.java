package net.woden.wdsit.controller;

import net.woden.wdsit.entity.GenSegmentEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.GenSegmentService;
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
@RequestMapping("genSegment/")
public class GenSegmentController {
 
    @Autowired
    private GenSegmentService genSegmentS; 

    @PostMapping(value="create")
    public ResponseEntity create(@RequestBody GenSegmentEntity g){
        ResponseModel response=this.genSegmentS.create(g); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="update")
    public ResponseEntity update(@RequestBody GenSegmentEntity g){
        ResponseModel response=this.genSegmentS.update(g); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{segmentId}")
    public ResponseEntity delete(@PathVariable int segmentId){
        ResponseModel response=this.genSegmentS.delete(segmentId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findByIncomeActive")
    public ResponseEntity findByIncomeActive(){
        ResponseModel response=this.genSegmentS.findByIncomeActive(); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findById/{segmentId}")
    public ResponseEntity findById(@PathVariable int segmentId){
        ResponseModel response=this.genSegmentS.findById(segmentId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list")
    public ResponseEntity list(){
        ResponseModel response=this.genSegmentS.list(); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findAll")
    public ResponseEntity findAll(){
        ResponseModel response=this.genSegmentS.findAll(); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
