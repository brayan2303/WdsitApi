package net.woden.wdsit.controller;

import net.woden.wdsit.entity.InvCyclicEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.InvCyclicService;
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
@RequestMapping("invCyclic/")
public class InvCyclicController {
 
    @Autowired
    private InvCyclicService invCyclicS; 

    @PostMapping(value="create")
    public ResponseEntity create(@RequestBody InvCyclicEntity i){
        ResponseModel response=this.invCyclicS.create(i); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="update")
    public ResponseEntity update(@RequestBody InvCyclicEntity i){
        ResponseModel response=this.invCyclicS.update(i); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="delete/{cyclicId}")
    public ResponseEntity delete(@PathVariable int cyclicId){
        ResponseModel response=this.invCyclicS.delete(cyclicId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value="approveReject/{cyclicId}/{status}")
    public ResponseEntity approveReject(@PathVariable int cyclicId,@PathVariable String status){
        ResponseModel response=this.invCyclicS.approveReject(cyclicId,status); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findById/{cyclicId}")
    public ResponseEntity findById(@PathVariable int cyclicId){
        ResponseModel response=this.invCyclicS.findById(cyclicId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="findLocation/{cyclicId}/{system}/{typeSampling}/{type}/{customer}")
    public ResponseEntity findLocation(@PathVariable int cyclicId,@PathVariable String system,@PathVariable String typeSampling,@PathVariable String type,@PathVariable String customer){
        ResponseModel response=this.invCyclicS.findLocation(cyclicId,system,typeSampling,type,customer); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="list/{customerId}/{personId}/{type}")
    public ResponseEntity list(@PathVariable int customerId,@PathVariable int personId,@PathVariable String type){
        ResponseModel response=this.invCyclicS.list(customerId,personId,type); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="listByCustomerId/{customerId}")
    public ResponseEntity listByCustomerId(@PathVariable int customerId){
        ResponseModel response=this.invCyclicS.listByCustomerId(customerId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="totalPallets/{cyclicId}")
    public ResponseEntity totalPallets(@PathVariable int cyclicId){
        ResponseModel response=this.invCyclicS.totalPallets(cyclicId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="totalSerials/{cyclicId}")
    public ResponseEntity totalSerials(@PathVariable int cyclicId){
        ResponseModel response=this.invCyclicS.totalSerials(cyclicId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="totalAccesories/{cyclicId}")
    public ResponseEntity totalAccesories(@PathVariable int cyclicId){
        ResponseModel response=this.invCyclicS.totalAccesories(cyclicId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="samplingPallets/{cyclicId}")
    public ResponseEntity samplingPallets(@PathVariable int cyclicId){
        ResponseModel response=this.invCyclicS.samplingPallets(cyclicId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="samplingSerials/{cyclicId}")
    public ResponseEntity samplingSerials(@PathVariable int cyclicId){
        ResponseModel response=this.invCyclicS.samplingSerials(cyclicId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="samplingAccesories/{cyclicId}")
    public ResponseEntity samplingAccesories(@PathVariable int cyclicId){
        ResponseModel response=this.invCyclicS.samplingAccesories(cyclicId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="audited/{cyclicId}/{type}")
    public ResponseEntity audited(@PathVariable int cyclicId,@PathVariable String type){
        ResponseModel response=this.invCyclicS.audited(cyclicId,type); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="layout/{system}/{customer}")
    public ResponseEntity layout(@PathVariable String system,@PathVariable String customer){
        ResponseModel response=this.invCyclicS.layout(system,customer); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="locationCouting/{cyclicId}")
    public ResponseEntity locationCouting(@PathVariable int cyclicId){
        ResponseModel response=this.invCyclicS.locationCouting(cyclicId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value="sapCodeSerial/{cyclicId}")
    public ResponseEntity sapCodeSerial(@PathVariable int cyclicId){
        ResponseModel response=this.invCyclicS.sapCodeSerial(cyclicId); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
