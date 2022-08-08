package net.woden.wdsit.controller;

import net.woden.wdsit.entity.BscAdvanceEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.BscAdvanceService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("bscAdvance/")
public class BscAdvanceController {
 
    @Autowired
    private BscAdvanceService bscAdvanceS; 

    @PostMapping(value="create")
    public ResponseEntity create(@RequestBody BscAdvanceEntity b){
        ResponseModel response=this.bscAdvanceS.create(b); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="update")
    public ResponseEntity update(@RequestBody BscAdvanceEntity b){
        ResponseModel response=this.bscAdvanceS.update(b); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{itemId}")
    public ResponseEntity delete(@PathVariable int itemId){
        ResponseModel response=this.bscAdvanceS.delete(itemId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list/{advanceId}")
    public ResponseEntity list(@PathVariable int advanceId){
        ResponseModel response=this.bscAdvanceS.list(advanceId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PostMapping(value="loadFile/{activityId}/{advanceId}")
    public ResponseEntity loadFile(@PathVariable int activityId,@PathVariable int advanceId,@RequestParam("file") MultipartFile file){
        ResponseModel response=this.bscAdvanceS.loadFile(activityId,advanceId,file); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="listFile/{activityId}")
    public ResponseEntity listFile(@PathVariable int activityId){
        ResponseModel response=this.bscAdvanceS.listFile(activityId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="deleteFile/{activityId}/{advanceId}/{fileName}")
    public ResponseEntity deleteFile(@PathVariable int activityId,@PathVariable int advanceId,@PathVariable String fileName){
        ResponseModel response=this.bscAdvanceS.deleteFile(activityId,advanceId,fileName); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="deleteFileItem/{activityId}/{advanceId}")
    public ResponseEntity deleteFileItem(@PathVariable int activityId,@PathVariable int advanceId){
        ResponseModel response=this.bscAdvanceS.deleteFileItem(activityId,advanceId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="deleteFileActivity/{activityId}")
    public ResponseEntity deleteFileActivity(@PathVariable int activityId){
        ResponseModel response=this.bscAdvanceS.deleteFileActivity(activityId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
