package net.woden.wdsit.controller;

import net.woden.wdsit.entity.BscFormulaVariableEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.BscFormulaVariableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("bscFormulaVariable/")
public class BscFormulaVariableController {
 
    @Autowired
    private BscFormulaVariableService bscFormulaVariableS; 

    @PostMapping(value="create")
    public ResponseEntity create(@RequestBody BscFormulaVariableEntity b){
        ResponseModel response=this.bscFormulaVariableS.create(b); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{formulaVariableId}")
    public ResponseEntity delete(@PathVariable int formulaVariableId){
        ResponseModel response=this.bscFormulaVariableS.delete(formulaVariableId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list/{formulaId}")
    public ResponseEntity list(@PathVariable int formulaId){
        ResponseModel response=this.bscFormulaVariableS.list(formulaId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="listMeasurementId/{measurementId}")
    public ResponseEntity listMeasurementId(@PathVariable int measurementId){
        ResponseModel response=this.bscFormulaVariableS.listMeasurementId(measurementId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
