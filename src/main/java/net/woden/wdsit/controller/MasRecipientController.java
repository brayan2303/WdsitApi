package net.woden.wdsit.controller;

import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.MasRecipientService;
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
@RequestMapping("masRecipient/")
public class MasRecipientController {
 
    @Autowired
    private MasRecipientService masRecipientS; 

    @PostMapping(value="create/{mailId}/{creationUserId}")
    public ResponseEntity create(@PathVariable int mailId,@PathVariable int creationUserId,@RequestParam("file") MultipartFile file){
        ResponseModel response=this.masRecipientS.create(mailId,creationUserId,file); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{mailId}")
    public ResponseEntity delete(@PathVariable int mailId){
        ResponseModel response=this.masRecipientS.delete(mailId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list/{mailId}")
    public ResponseEntity list(@PathVariable int mailId){
        ResponseModel response=this.masRecipientS.list(mailId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
