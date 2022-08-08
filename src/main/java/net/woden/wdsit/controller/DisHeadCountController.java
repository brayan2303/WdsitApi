package net.woden.wdsit.controller;

import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.DisHeadCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("disHeadCount/")
public class DisHeadCountController {
 
    @Autowired
    private DisHeadCountService disHeadCountS; 

    @PostMapping(value="create/{year}/{month}")
    public ResponseEntity create(@PathVariable int year,@PathVariable int month,@RequestParam("file") MultipartFile file){
        ResponseModel response=this.disHeadCountS.create(year,month,file); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{year}/{month}")
    public ResponseEntity delete(@PathVariable int year,@PathVariable int month){
        ResponseModel response=this.disHeadCountS.delete(year,month); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list")
    public ResponseEntity list(){
        ResponseModel response=this.disHeadCountS.list(); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findByYearMonth/{year}/{month}")
    public ResponseEntity findByYearMonth(@PathVariable int year,@PathVariable int month){
        ResponseModel response=this.disHeadCountS.findByYearMonth(year,month); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
