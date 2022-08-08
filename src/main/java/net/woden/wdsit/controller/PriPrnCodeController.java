package net.woden.wdsit.controller;

import net.woden.wdsit.entity.PriPrnCodeEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.PriPrnCodeService;
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
@RequestMapping("priPrnCode/")
public class PriPrnCodeController {
 
    @Autowired
    private PriPrnCodeService priPrnCodeS; 

    @PostMapping(value="create")
    public ResponseEntity create(@RequestBody PriPrnCodeEntity p){
        ResponseModel response=this.priPrnCodeS.create(p); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="update")
    public ResponseEntity update(@RequestBody PriPrnCodeEntity p){
        ResponseModel response=this.priPrnCodeS.update(p); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{prnCodeId}")
    public ResponseEntity delete(@PathVariable int prnCodeId){
        ResponseModel response=this.priPrnCodeS.delete(prnCodeId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findByLabelId/{labelId}")
    public ResponseEntity findByLabelId(@PathVariable int labelId){
        ResponseModel response=this.priPrnCodeS.findByLabelId(labelId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
