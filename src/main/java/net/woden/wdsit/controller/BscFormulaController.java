package net.woden.wdsit.controller;

import net.woden.wdsit.entity.BscFormulaEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.BscFormulaService;
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
@RequestMapping("bscFormula/")
public class BscFormulaController {
 
    @Autowired
    private BscFormulaService bscFormulaS; 

    @PostMapping(value="create")
    public ResponseEntity create(@RequestBody BscFormulaEntity b){
        ResponseModel response=this.bscFormulaS.create(b); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="update")
    public ResponseEntity update(@RequestBody BscFormulaEntity b){
        ResponseModel response=this.bscFormulaS.update(b); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{formulaId}")
    public ResponseEntity delete(@PathVariable int formulaId){
        ResponseModel response=this.bscFormulaS.delete(formulaId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list")
    public ResponseEntity list(){
        ResponseModel response=this.bscFormulaS.list(); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="listActive")
    public ResponseEntity listActive(){
        ResponseModel response=this.bscFormulaS.listActive(); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
