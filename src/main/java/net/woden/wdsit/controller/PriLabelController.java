package net.woden.wdsit.controller;

import java.util.ArrayList;
import net.woden.wdsit.entity.InvCardEntity;
import net.woden.wdsit.entity.PriLabelEntity;
import net.woden.wdsit.model.PriLabelWModel;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.PriLabelService;
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
@RequestMapping("priLabel/")
public class PriLabelController {

    @Autowired
    private PriLabelService priLabelS;

    @PostMapping(value = "create")
    public ResponseEntity create(@RequestBody PriLabelEntity p) {
        ResponseModel response = this.priLabelS.create(p);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping(value = "update")
    public ResponseEntity update(@RequestBody PriLabelEntity p) {
        ResponseModel response = this.priLabelS.update(p);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping(value = "delete/{labelId}")
    public ResponseEntity delete(@PathVariable int labelId) {
        ResponseModel response = this.priLabelS.delete(labelId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "findByCustomerId/{customerId}")
    public ResponseEntity findByCustomerId(@PathVariable int customerId) {
        ResponseModel response = this.priLabelS.findByCustomerId(customerId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "findById/{id}")
    public ResponseEntity findById(@PathVariable int id) {
        ResponseModel response = this.priLabelS.findById(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "findSerial/{serial}/{countryId}/{customerId}")
    public ResponseEntity findSerial(@PathVariable String serial, @PathVariable int countryId, @PathVariable int customerId) {
        ResponseModel response = this.priLabelS.findSerial(serial, countryId, customerId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "findPallet/{countryId}/{customerId}/{pallet}/{caja}")
    public ResponseEntity findPallet(@PathVariable int countryId, @PathVariable int customerId, @PathVariable String pallet, @PathVariable int caja) {
        ResponseModel response = this.priLabelS.findPallet(customerId, countryId, pallet, caja);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "findPalletUnreadable/{pallet}")
    public ResponseEntity findPalletUnreadable(@PathVariable String pallet) {
        ResponseModel response = this.priLabelS.findPalletUnreadable(pallet);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value = "print/{printerId}/{labelId}")
    public ResponseEntity print(@PathVariable int printerId, @PathVariable int labelId, @RequestBody PriLabelWModel p) {
        ResponseModel response = this.priLabelS.print(printerId, labelId, p);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value = "printCard/{printerId}/{labelName}")
    public ResponseEntity printCard(@PathVariable int printerId, @PathVariable String labelName, @RequestBody ArrayList<InvCardEntity> array) {
        ResponseModel response = this.priLabelS.printCard(printerId, labelName, array);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "list")
    public ResponseEntity list() {
        ResponseModel response = this.priLabelS.list();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "printHistory/{userId}/{pallet}/{box}")
    public ResponseEntity printHistory(@PathVariable int userId, @PathVariable String pallet, @PathVariable int box) {
        ResponseModel response = this.priLabelS.printHistory(userId, pallet, box);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value = "createHistory/{userId}/{pallet}/{box}")
    public ResponseEntity createHistory(@PathVariable int userId, @PathVariable String pallet, @PathVariable int box) {
        ResponseModel response = this.priLabelS.createHistory(userId, pallet, box);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "userPermission/list")
    public ResponseEntity userPermissionList() {
        ResponseModel response = this.priLabelS.userPermissionList();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping(value = "userPermission/delete/{userId}")
    public ResponseEntity userPermissionDelete(@PathVariable int userId) {
        ResponseModel response = this.priLabelS.userPermissionDelete(userId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value = "userPermission/create/{userId}")
    public ResponseEntity userPermissionCreate(@PathVariable int userId) {
        ResponseModel response = this.priLabelS.userPermissionCreate(userId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "validationSerial/{serial}/{userId}")
    public ResponseEntity validationSerial(@PathVariable String serial, @PathVariable int userId) {
        ResponseModel response = this.priLabelS.validationSerial(serial, userId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "listValidation/{serial}/{userId}")
    public ResponseEntity listValidation(@PathVariable String serial, @PathVariable int userId) {
        ResponseModel response = this.priLabelS.listValidation(serial, userId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @DeleteMapping(value = "deleteSeries")
    public ResponseEntity deleteSeries() {
        ResponseModel response = this.priLabelS.deleteSeries();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
