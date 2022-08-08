package net.woden.wdsit.controller;

import net.woden.wdsit.entity.SchClienteEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.SchClienteService;
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
@RequestMapping("schCliente/")
public class SchClienteController {
 
    @Autowired
    private SchClienteService schClienteS; 

    @PostMapping(value="create")
    public ResponseEntity create(@RequestBody SchClienteEntity s){
        ResponseModel response=this.schClienteS.create(s); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="update")
    public ResponseEntity update(@RequestBody SchClienteEntity s){
        ResponseModel response=this.schClienteS.update(s); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{clienteId}")
    public ResponseEntity delete(@PathVariable int clienteId){
        ResponseModel response=this.schClienteS.delete(clienteId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list")
    public ResponseEntity list(){
        ResponseModel response=this.schClienteS.list(); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
