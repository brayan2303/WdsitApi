package net.woden.wdsit.controller;

import net.woden.wdsit.entity.MeeSupportEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.MeeSupportService;
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
@RequestMapping("meeSupport/")
public class MeeSupportController {
    @Autowired
    private MeeSupportService meeSupportS;
    
    @PostMapping("create")
    public ResponseEntity create(@RequestBody MeeSupportEntity m){
        ResponseModel response=this.meeSupportS.create(m);
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping("update")
    public ResponseEntity update(@RequestBody MeeSupportEntity m){
        ResponseModel response=this.meeSupportS.update(m);
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity delete(@PathVariable int id){
        ResponseModel response=this.meeSupportS.delete(id);
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping("findById/{supportId}")
    public ResponseEntity findById(@PathVariable int supportId){
        ResponseModel response=this.meeSupportS.findById(supportId);
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping("list/{meetingId}")
    public ResponseEntity list(@PathVariable int meetingId){
        ResponseModel response=this.meeSupportS.list(meetingId);
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping("listByUserId/{responsibleUserId}/{startDate}/{endDate}/{states}")
    public ResponseEntity listByUserId(@PathVariable int responsibleUserId,@PathVariable String startDate,@PathVariable String endDate,@PathVariable String states){
        ResponseModel response=this.meeSupportS.listByUserId(responsibleUserId,startDate,endDate, states);
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping("listByCreationUserId/{meetingId}/{creationUserId}")
    public ResponseEntity listByCreationUserId(@PathVariable int meetingId,@PathVariable int creationUserId){
        ResponseModel response=this.meeSupportS.listByCreationUserId(meetingId,creationUserId);
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PostMapping(value="loadFile/{meetingId}/{supportId}")
    public ResponseEntity loadFile(@PathVariable int meetingId,@PathVariable int supportId,@RequestParam("file") MultipartFile file){
        ResponseModel response=this.meeSupportS.loadFile(meetingId,supportId,file); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping("listFiles/{meetingId}/{supportId}")
    public ResponseEntity listFiles(@PathVariable int meetingId,@PathVariable int supportId){
        ResponseModel response=this.meeSupportS.listFiles(meetingId,supportId);
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping("deleteFile/{meetingId}/{supportId}/{fileName}")
    public ResponseEntity deleteFile(@PathVariable int meetingId,@PathVariable int supportId,@PathVariable String fileName){
        ResponseModel response=this.meeSupportS.deleteFile(meetingId,supportId,fileName);
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PostMapping("sendEmail/{supportId}/{remitenteId}/{destinatarioId}")
    public ResponseEntity sendEmail(@PathVariable int supportId,@PathVariable int remitenteId,@PathVariable int destinatarioId){
        ResponseModel response=this.meeSupportS.sendEmail(supportId,remitenteId,destinatarioId);
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
     @GetMapping("listStatus/{states}")
    public ResponseEntity listStatus(@PathVariable String states){
        ResponseModel response=this.meeSupportS.listStatus(states);
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
     @PostMapping("sendEmailStatus/{supportId}/{remitenteId}/{destinatarioId}/{status}")
    public ResponseEntity sendEmailStatus(@PathVariable int supportId,@PathVariable int remitenteId,@PathVariable int destinatarioId,@PathVariable String status){
        ResponseModel response=this.meeSupportS.sendEmailStatus(supportId,remitenteId,destinatarioId,status);
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
     @GetMapping("findByStatus/{status}")
    public ResponseEntity findByStatus(@PathVariable String status){
        ResponseModel response=this.meeSupportS.findByStatus(status);
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
