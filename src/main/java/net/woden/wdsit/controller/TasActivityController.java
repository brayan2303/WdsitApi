package net.woden.wdsit.controller;

import net.woden.wdsit.entity.TasActivityEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.TasActivityService;
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
@RequestMapping("tasActivity/")
public class TasActivityController {
 
    @Autowired
    private TasActivityService tasActivityS; 

    @PostMapping(value="create")
    public ResponseEntity create(@RequestBody TasActivityEntity t){
        ResponseModel response=this.tasActivityS.create(t); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="update")
    public ResponseEntity update(@RequestBody TasActivityEntity t){
        ResponseModel response=this.tasActivityS.update(t); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{activityId}")
    public ResponseEntity delete(@PathVariable int activityId){
        ResponseModel response=this.tasActivityS.delete(activityId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="close/{activityId}")
    public ResponseEntity close(@PathVariable int activityId){
        ResponseModel response=this.tasActivityS.close(activityId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findClose/{taskId}")
    public ResponseEntity findClose(@PathVariable int taskId){
        ResponseModel response=this.tasActivityS.findClose(taskId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findById/{activityId}")
    public ResponseEntity findById(@PathVariable int activityId){
        ResponseModel response=this.tasActivityS.findById(activityId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list/{taskId}")
    public ResponseEntity list(@PathVariable int taskId){
        ResponseModel response=this.tasActivityS.list(taskId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PostMapping(value="loadFile/{taskId}/{activityId}")
    public ResponseEntity loadFile(@PathVariable String taskId,@PathVariable String activityId,@RequestParam("files") MultipartFile[] files){
        ResponseModel response=this.tasActivityS.loadFile(taskId,activityId,files); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="listFile/{taskId}")
    public ResponseEntity listFile(@PathVariable String taskId){
        ResponseModel response=this.tasActivityS.listFile(taskId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="deleteFile/{taskId}/{activityId}/{fileName}")
    public ResponseEntity deleteFile(@PathVariable String taskId,@PathVariable String activityId,@PathVariable String fileName){
        ResponseModel response=this.tasActivityS.deleteFile(taskId,activityId,fileName); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="deleteFileByActivityId/{taskId}/{activityId}")
    public ResponseEntity deleteFileByActivityId(@PathVariable String taskId,@PathVariable String activityId){
        ResponseModel response=this.tasActivityS.deleteFileByActivityId(taskId,activityId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="deleteFileByTaskId/{taskId}")
    public ResponseEntity deleteFileByTaskId(@PathVariable String taskId){
        ResponseModel response=this.tasActivityS.deleteFileByTaskId(taskId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
