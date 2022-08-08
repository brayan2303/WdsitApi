package net.woden.wdsit.controller;

import net.woden.wdsit.entity.CovFormEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.CovFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("CovForm/")
public class CovFormController {

    @Autowired
    private CovFormService CovForm;

    @PostMapping(value = "create") // servicio crear y almacenar
    public ResponseEntity create(@RequestBody CovFormEntity a) {
        ResponseModel response = this.CovForm.create(a);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping(value = "update") //servicio actualizar
    public ResponseEntity update(@RequestBody CovFormEntity a) {
        ResponseModel response = this.CovForm.update(a);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value = "activeInactive/{id}/{status}") //servicio actualizar
    public ResponseEntity activeInactive(@PathVariable int id,@PathVariable boolean status) {
        ResponseModel response = this.CovForm.activeInactive(id,status);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value = "delete/{id}") //servicio eliminar
    public ResponseEntity delete(@PathVariable int id) {
            ResponseModel response = this.CovForm.delete(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "list") //servicio mostrar en pantalla
    public ResponseEntity list() {
        ResponseModel response = this.CovForm.list();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "findById/{id}") //servicio mostrar en pantalla
    public ResponseEntity findById(@PathVariable int id) {
        ResponseModel response = this.CovForm.findById(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value = "loadFile/{identification}/{type}/{creationDate}")
    public ResponseEntity loadFile(@PathVariable int identification,@PathVariable String type, @PathVariable String creationDate, @RequestParam("files") MultipartFile[] files) {
        ResponseModel response = this.CovForm.loadFile(identification, type,creationDate, files);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
   @GetMapping(value="listFile/{identification}/{type}/{creationDate}")
    public ResponseEntity listFile(@PathVariable int identification, @PathVariable String type, @PathVariable String creationDate){
        ResponseModel response=this.CovForm.listFile(identification,type,creationDate); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
       @PutMapping(value = "updateinitialization/{id}")
    public ResponseEntity updateinitialization(@PathVariable int id, @RequestBody CovFormEntity c) {
        ResponseModel response = this.CovForm.updateinitialization(id, c);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
          @PutMapping(value = "updateAnnexed/{id}")
    public ResponseEntity updateAnnexed(@PathVariable int id, @RequestBody CovFormEntity c) {
        ResponseModel response = this.CovForm.updateAnnexed(id, c);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
          @PutMapping(value = "updateProof/{id}")
    public ResponseEntity updateProof(@PathVariable int id, @RequestBody CovFormEntity c) {
        ResponseModel response = this.CovForm.updateProof(id, c);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
          @PutMapping(value = "updateProofSecond/{id}")
    public ResponseEntity updateProofSecond(@PathVariable int id, @RequestBody CovFormEntity c) {
        ResponseModel response = this.CovForm.updateProofSecond(id, c);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
      @PutMapping(value = "updateProofThird/{id}")
    public ResponseEntity updateProofThird(@PathVariable int id, @RequestBody CovFormEntity c) {
        ResponseModel response = this.CovForm.updateProofThird(id, c);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
     @PutMapping(value = "updateFollowUp/{id}")
    public ResponseEntity updateFollowUp(@PathVariable int id, @RequestBody CovFormEntity c) {
        ResponseModel response = this.CovForm.updateFollowUp(id, c);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
      @PutMapping(value = "updateWorking/{id}")
    public ResponseEntity updateWorking(@PathVariable int id, @RequestBody CovFormEntity c) {
        ResponseModel response = this.CovForm.updateWorking(id, c);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value = "updateObservation/{id}")
    public ResponseEntity updateObservation(@PathVariable int id, @RequestBody CovFormEntity c) {
        ResponseModel response = this.CovForm.updateObservation(id, c);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value = "initializatioFindById/{id}")
    public ResponseEntity initializatioFindById(@PathVariable int id) {
        ResponseModel response = this.CovForm.initializatioFindById(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value = "annexedFindById/{id}")
    public ResponseEntity annexedFindById(@PathVariable int id) {
        ResponseModel response = this.CovForm.annexedFindById(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value = "proofFindById/{id}")
    public ResponseEntity proofFindById(@PathVariable int id) {
        ResponseModel response = this.CovForm.proofFindById(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
     @GetMapping(value = "proofSecondFindById/{id}")
    public ResponseEntity ProofSecondFindById(@PathVariable int id) {
        ResponseModel response = this.CovForm.proofSecondFindById(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value = "proofThirdFindById/{id}")
    public ResponseEntity ProofThirdFindById(@PathVariable int id) {
        ResponseModel response = this.CovForm.proofThirdFindById(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value = "proofFollowUpFindById/{id}")
    public ResponseEntity proofFollowUpFindById(@PathVariable int id) {
        ResponseModel response = this.CovForm.proofFollowUpFindById(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(value = "workingFindById/{id}")
    public ResponseEntity workingFindById(@PathVariable int id) {
        ResponseModel response = this.CovForm.workingFindById(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
     @GetMapping(value = "observationFindById/{id}")
    public ResponseEntity observationFindById(@PathVariable int id) {
        ResponseModel response = this.CovForm.observationFindById(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
     @GetMapping(value = "initializatioFindByIdArray/{id}")
    public ResponseEntity initializatioFindByIdArray(@PathVariable int id) {
        ResponseModel response = this.CovForm.initializatioFindByIdArray(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
      @GetMapping(value = "annexedFindByIdArray/{id}")
    public ResponseEntity annexedFindByIdArray(@PathVariable int id) {
        ResponseModel response = this.CovForm.annexedFindByIdArray(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
      @GetMapping(value = "proofFindByIdArray/{id}")
    public ResponseEntity proofFindByIdArray(@PathVariable int id) {
        ResponseModel response = this.CovForm.proofFindByIdArray(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
      @GetMapping(value = "proofSecondFindByIdArray/{id}")
    public ResponseEntity proofSecondFindByIdArray(@PathVariable int id) {
        ResponseModel response = this.CovForm.proofSecondFindByIdArray(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
      @GetMapping(value = "proofThirdFindByIdArray/{id}")
    public ResponseEntity proofThirdFindByIdArray(@PathVariable int id) {
        ResponseModel response = this.CovForm.proofThirdFindByIdArray(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
      @GetMapping(value = "proofFollowUpFindByIdArray/{id}")
    public ResponseEntity proofFollowUpFindByIdArray(@PathVariable int id) {
        ResponseModel response = this.CovForm.proofFollowUpFindByIdArray(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
      @GetMapping(value = "workingFindByIdArray/{id}")
    public ResponseEntity workingFindByIdArray(@PathVariable int id) {
        ResponseModel response = this.CovForm.workingFindByIdArray(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
      @GetMapping(value = "observationFindByIdArray/{id}")
    public ResponseEntity observationFindByIdArray(@PathVariable int id) {
        ResponseModel response = this.CovForm.observationFindByIdArray(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
      @GetMapping(value = "listCustomer") //servicio mostrar en pantalla
    public ResponseEntity listCustomer() {
        ResponseModel response = this.CovForm.listCustomer();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
      @GetMapping(value = "listSegment") //servicio mostrar en pantalla
    public ResponseEntity listSegment() {
        ResponseModel response = this.CovForm.listSegment();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
      @GetMapping(value = "listCostCenter") //servicio mostrar en pantalla
    public ResponseEntity listCostCenter() {
        ResponseModel response = this.CovForm.listCostCenter();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
      @GetMapping(value = "listAll/{id}") //servicio mostrar en pantalla
    public ResponseEntity listAll(@PathVariable int id) {
        ResponseModel response = this.CovForm.listAll(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
