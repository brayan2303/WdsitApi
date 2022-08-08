package net.woden.wdsit.controller;

import net.woden.wdsit.entity.WlsServerEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.WlsServerService;
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
@RequestMapping("wlsServer/")
public class WlsServerController {
 
    @Autowired
    private WlsServerService wlsServerS; 

    @PostMapping(value="create")
    public ResponseEntity create(@RequestBody WlsServerEntity w){
        ResponseModel response=this.wlsServerS.create(w); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="update")
    public ResponseEntity update(@RequestBody WlsServerEntity w){
        ResponseModel response=this.wlsServerS.update(w); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{id}")
    public ResponseEntity delete(@PathVariable int id){
        ResponseModel response=this.wlsServerS.delete(id); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PostMapping(value="dataBaseCreate")
    public ResponseEntity dataBaseCreate(@RequestBody String dataBaseName){
        ResponseModel response=this.wlsServerS.dataBaseCreate(dataBaseName); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="find/{ip}")
    public ResponseEntity find(@PathVariable String ip){
        ResponseModel response=this.wlsServerS.find(ip); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list")
    public ResponseEntity list(){
        ResponseModel response=this.wlsServerS.list(); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="dataBase/{ip}")
    public ResponseEntity dataBase(@PathVariable String ip){
        ResponseModel response=this.wlsServerS.dataBase(ip); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="table/{ip}/{dataBase}")
    public ResponseEntity table(@PathVariable String ip,@PathVariable String dataBase){
        ResponseModel response=this.wlsServerS.table(ip,dataBase); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="column/{dataBaseName}/{tableName}")
    public ResponseEntity column(@PathVariable String dataBaseName,@PathVariable String tableName){
        ResponseModel response=this.wlsServerS.column(dataBaseName,tableName); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="testConnection/{ip}/{dataBase}")
    public ResponseEntity testConnection(@PathVariable String ip,@PathVariable String dataBase){
        ResponseModel response=this.wlsServerS.testConnection(ip,dataBase); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
