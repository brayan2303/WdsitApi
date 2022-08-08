package net.woden.wdsit.controller;

import net.woden.wdsit.entity.InvCoutingEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.InvCoutingService;
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
@RequestMapping("invCouting/")
public class InvCoutingController {
 
    @Autowired
    private InvCoutingService invCoutingS; 

    @PostMapping(value="create/{cardId}")
    public ResponseEntity create(@PathVariable int cardId,@RequestBody InvCoutingEntity i){
        ResponseModel response=this.invCoutingS.create(cardId,i); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="update")
    public ResponseEntity update(@RequestBody InvCoutingEntity i){
        ResponseModel response=this.invCoutingS.update(i); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{id}")
    public ResponseEntity delete(@PathVariable int id){
        ResponseModel response=this.invCoutingS.delete(id); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="addQuantity/{coutingId}/{quantity}")
    public ResponseEntity addQuantity(@PathVariable int coutingId,@PathVariable int quantity){
        ResponseModel response=this.invCoutingS.addQuantity(coutingId,quantity); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="deleteQuantity/{coutingId}")
    public ResponseEntity deleteQuantity(@PathVariable int coutingId){
        ResponseModel response=this.invCoutingS.deleteQuantity(coutingId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="startEnd/{cyclicCoutingId}/{status}")
    public ResponseEntity startEnd(@PathVariable int cyclicCoutingId,@PathVariable String status){
        ResponseModel response=this.invCoutingS.startEnd(cyclicCoutingId,status); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="openClose/{cyclicCoutingId}/{status}")
    public ResponseEntity openClose(@PathVariable int cyclicCoutingId,@PathVariable String status){
        ResponseModel response=this.invCoutingS.openClose(cyclicCoutingId,status); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findQuantity/{coutingId}")
    public ResponseEntity findQuantity(@PathVariable int coutingId){
        ResponseModel response=this.invCoutingS.findQuantity(coutingId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="validatePallet/{cyclicId}/{pallet}/{creationUserId}")
    public ResponseEntity validatePallet(@PathVariable int cyclicId,@PathVariable String pallet,@PathVariable int creationUserId){
        ResponseModel response=this.invCoutingS.validatePallet(cyclicId,pallet,creationUserId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findAll/{customer}/{palletId}")
    public ResponseEntity findAll(@PathVariable String customer,@PathVariable int palletId){
        ResponseModel response=this.invCoutingS.findAll(customer,palletId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findPending/{cyclicPalletId}")
    public ResponseEntity findPending(@PathVariable int cyclicPalletId){
        ResponseModel response=this.invCoutingS.findPending(cyclicPalletId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list/{customerId}/{personId}")
    public ResponseEntity list(@PathVariable int customerId,@PathVariable int personId){
        ResponseModel response=this.invCoutingS.list(customerId,personId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findByPalletId/{palletId}")
    public ResponseEntity findByPalletId(@PathVariable int palletId){
        ResponseModel response=this.invCoutingS.findByPalletId(palletId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="total/{coutingId}/{number}")
    public ResponseEntity total(@PathVariable int coutingId,@PathVariable String number){
        ResponseModel response=this.invCoutingS.total(coutingId,number); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="income/{cyclicId}/{pallet}")
    public ResponseEntity income(@PathVariable int cyclicId,@PathVariable String pallet){
        ResponseModel response=this.invCoutingS.income(cyclicId,pallet); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="remaining/{coutingId}/{number}")
    public ResponseEntity remaining(@PathVariable int coutingId,@PathVariable String number){
        ResponseModel response=this.invCoutingS.remaining(coutingId,number); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="sapCode/{cyclicId}")
    public ResponseEntity sapCode(@PathVariable int cyclicId){
        ResponseModel response=this.invCoutingS.sapCode(cyclicId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PostMapping(value="print/{customer}")
    public ResponseEntity print(@PathVariable String customer,@RequestBody InvCoutingEntity i){
        ResponseModel response=this.invCoutingS.print(customer,i); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
