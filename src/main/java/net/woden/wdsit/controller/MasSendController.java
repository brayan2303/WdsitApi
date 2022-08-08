package net.woden.wdsit.controller;

import net.woden.wdsit.entity.MasSendEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.MasSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("masSend/")
public class MasSendController {
 
    @Autowired
    private MasSendService masSendS; 

    @PostMapping(value="create")
    public ResponseEntity create(@RequestBody MasSendEntity m){
        ResponseModel response=this.masSendS.create(m); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="approval/{id}")
    public ResponseEntity approval(@PathVariable int id){
        ResponseModel response=this.masSendS.approval(id); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="approveReject/{id}/{message}")
    public ResponseEntity approveReject(@PathVariable int id,@PathVariable String message){
        ResponseModel response=this.masSendS.approveReject(id,message); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="find/{mailId}")
    public ResponseEntity find(@PathVariable int mailId){
        ResponseModel response=this.masSendS.find(mailId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list/{mailId}/{approvalUserId}")
    public ResponseEntity list(@PathVariable int mailId,@PathVariable int approvalUserId){
        ResponseModel response=this.masSendS.list(mailId,approvalUserId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
