package net.woden.wdsit.controller;

import net.woden.wdsit.entity.PqrPqrsEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.PqrPqrsService;
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
@RequestMapping("pqrPqrs/")
public class PqrPqrsController {

    @Autowired
    private PqrPqrsService pqrPqrsS;

    @PostMapping(value = "create/{countryGeneralId}")
    public ResponseEntity create(@RequestBody PqrPqrsEntity p, @PathVariable int countryGeneralId) {
        ResponseModel response = this.pqrPqrsS.create(p, countryGeneralId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping(value = "update")
    public ResponseEntity update(@RequestBody PqrPqrsEntity p) {
        ResponseModel response = this.pqrPqrsS.update(p);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping(value = "eventUpdate")
    public ResponseEntity eventUpdate(@RequestBody PqrPqrsEntity p) {
        ResponseModel response = this.pqrPqrsS.eventUpdate(p);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping(value = "delete/{pqrsId}")
    public ResponseEntity delete(@PathVariable int pqrsId) {
        ResponseModel response = this.pqrPqrsS.delete(pqrsId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "manage/{pqrsId}/{assignedPersonId}/{statusId}/{type}")
    public ResponseEntity manage(@PathVariable String pqrsId, @PathVariable int assignedPersonId, @PathVariable int statusId, @PathVariable String type) {
        ResponseModel response = this.pqrPqrsS.manage(pqrsId, assignedPersonId, statusId, type);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "finish/{pqrsId}/{managementStatusId}/{responseDateCustomerPqrs}/{finalContactMethodId}/{statusId}/{observations}/{procedureId}")
    public ResponseEntity finish(@PathVariable String pqrsId, @PathVariable int managementStatusId, @PathVariable String responseDateCustomerPqrs, @PathVariable int finalContactMethodId, @PathVariable int statusId, @PathVariable String observations, @PathVariable int procedureId) {
        ResponseModel response = this.pqrPqrsS.finish(pqrsId, managementStatusId, responseDateCustomerPqrs, finalContactMethodId, statusId, observations, procedureId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value = "find/{countryId}")
    public ResponseEntity find(@RequestParam String identificationNumber, @RequestParam String ticket, @RequestParam String numero, @RequestParam String serialImei, @PathVariable int countryId) {
        ResponseModel response = this.pqrPqrsS.find(identificationNumber, ticket, numero, serialImei, countryId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value = "findCustomer")
    public ResponseEntity findCustomer(@RequestParam int userId, @RequestParam String identificationNumber, @RequestParam String ticket, @RequestParam String numero, @RequestParam String serialImei, @RequestParam String name) {
        ResponseModel response = this.pqrPqrsS.findCustomer(userId, identificationNumber, ticket, numero, serialImei, name);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value = "findById")
    public ResponseEntity findById(@RequestParam String pqrsId) {
        ResponseModel response = this.pqrPqrsS.findById(pqrsId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value = "findByNumber")
    public ResponseEntity findByNumber(@RequestParam String number) {
        ResponseModel response = this.pqrPqrsS.findByNumber(number);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "list/{personId}/{status}/{initialDate}/{finalDate}/{number}/{countryId}")
    public ResponseEntity list(@PathVariable int personId, @PathVariable String status, @PathVariable String initialDate, @PathVariable String finalDate, @PathVariable String number, @PathVariable int countryId) {
        ResponseModel response = this.pqrPqrsS.list(personId, status, initialDate, finalDate, number, countryId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "listPerson/{personId}/{status}/{initialDate}/{finalDate}/{number}/{countryId}")
    public ResponseEntity listPerson(@PathVariable int personId, @PathVariable String status, @PathVariable String initialDate, @PathVariable String finalDate, @PathVariable String number, @PathVariable int countryId) {
        ResponseModel response = this.pqrPqrsS.listPerson(personId, status, initialDate, finalDate, number, countryId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "tat/{countryId}")
    public ResponseEntity tat(@PathVariable int countryId) {
        ResponseModel response = this.pqrPqrsS.tat(countryId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "tatAcido/{countryId}")
    public ResponseEntity tatAcido(@PathVariable int countryId) {
        ResponseModel response = this.pqrPqrsS.tatAcido(countryId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "totalMonth/{initialDate}/{finalDate}/{countryId}")
    public ResponseEntity totalMonth(@PathVariable String initialDate, @PathVariable String finalDate, @PathVariable int countryId) {
        ResponseModel response = this.pqrPqrsS.totalMonth(initialDate, finalDate, countryId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "totalAgent/{initialDate}/{finalDate}")
    public ResponseEntity totalAgent(@PathVariable String initialDate, @PathVariable String finalDate) {
        ResponseModel response = this.pqrPqrsS.totalAgent(initialDate, finalDate);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value = "loadFile/{pqrsNumber}/{type}")
    public ResponseEntity loadFile(@PathVariable String pqrsNumber, @PathVariable String type, @RequestParam("files") MultipartFile[] files) {
        ResponseModel response = this.pqrPqrsS.loadFile(pqrsNumber, type, files);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping(value = "deleteFile/{pqrsNumber}/{type}/{fileName}")
    public ResponseEntity deleteFile(@PathVariable String pqrsNumber, @PathVariable String type, @PathVariable String fileName) {
        ResponseModel response = this.pqrPqrsS.deleteFile(pqrsNumber, type, fileName);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping(value = "deleteFileByPqrs/{pqrsNumber}")
    public ResponseEntity deleteFileByPqrs(@PathVariable String pqrsNumber) {
        ResponseModel response = this.pqrPqrsS.deleteFileByPqrs(pqrsNumber);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "listFile/{pqrsNumber}/{type}")
    public ResponseEntity listFile(@PathVariable String pqrsNumber, @PathVariable String type) {
        ResponseModel response = this.pqrPqrsS.listFile(pqrsNumber, type);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping(value = "updateCustomerEscalation/{number}")
    public ResponseEntity updateCustomerEscalation(@PathVariable String number) {
        ResponseModel response = this.pqrPqrsS.updateCustomerEscalation(number);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping(value = "updateCustomerEscalationFinish/{number}")
    public ResponseEntity updateCustomerEscalationFinish(@PathVariable String number) {
        ResponseModel response = this.pqrPqrsS.updateCustomerEscalationFinish(number);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("sendEmail")
    public ResponseEntity sendEmail(@RequestParam String id, @RequestParam String number, @RequestParam int destinatarioId) {
        ResponseModel response = this.pqrPqrsS.sendEmail(id, number, destinatarioId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "escalationAgent/{number}")
    public ResponseEntity escalationAgent(@PathVariable String number) {
        ResponseModel response = this.pqrPqrsS.escalationAgent(number);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "listReport/{userId}/{initialDate}/{finalDate}")
    public ResponseEntity listReport(@PathVariable int userId, @PathVariable String initialDate, @PathVariable String finalDate) {
        ResponseModel response = this.pqrPqrsS.listReport(userId, initialDate, finalDate);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "searchTypeTicket/{number}")
    public ResponseEntity searchTypeTicket(@PathVariable String number) {
        ResponseModel response = this.pqrPqrsS.searchTypeTicket(number);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
