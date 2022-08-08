package net.woden.wdsit.controller;

import net.woden.wdsit.entity.InvCardEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.InvCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("invCard/")
public class InvCardController {
 
    @Autowired
    private InvCardService invCardS; 

    @PostMapping(value="create/{total}")
    public ResponseEntity create(@PathVariable int total,@RequestBody InvCardEntity i){
        ResponseModel response=this.invCardS.create(total,i); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{cardId}")
    public ResponseEntity delete(@PathVariable int cardId){
        ResponseModel response=this.invCardS.delete(cardId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list/{coutingId}")
    public ResponseEntity list(@PathVariable int coutingId){
        ResponseModel response=this.invCardS.list(coutingId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findAvailable/{cyclicId}/{quantity}")
    public ResponseEntity findAvailable(@PathVariable int cyclicId,@PathVariable int quantity){
        ResponseModel response=this.invCardS.findAvailable(cyclicId,quantity); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PostMapping(value="sign/{cardId}/{signed}")
    public ResponseEntity sign(@PathVariable int cardId,@PathVariable String signed,@RequestParam("file") String file){
        ResponseModel response=this.invCardS.sign(cardId,signed,file); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
