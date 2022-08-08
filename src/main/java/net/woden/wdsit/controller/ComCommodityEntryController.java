/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.controller;

import net.woden.wdsit.entity.ComCommodityEntryEntity;
import net.woden.wdsit.model.ComCommodityEntryPreAlertTCPIPModel;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.ComCommodityEntryService;
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

/**
 *
 * @author m.pulido
 */
@RestController
@RequestMapping("ComCommodityEntry/")
public class ComCommodityEntryController {

    @Autowired
    private ComCommodityEntryService CommCommodityEntryS;

    @PostMapping(value = "create/{userId}/{countryId}/{customerId}")
    public ResponseEntity create(@PathVariable int userId, @PathVariable int countryId, @PathVariable int customerId, @RequestBody ComCommodityEntryEntity c) {
        ResponseModel response = this.CommCommodityEntryS.create(userId, countryId, customerId, c);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping(value = "update/{comCommodityEntryId}")
    public ResponseEntity update(@PathVariable int comCommodityEntryId, @RequestBody ComCommodityEntryEntity c) {
        ResponseModel response = this.CommCommodityEntryS.update(comCommodityEntryId, c);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping(value = "closeEntry/{entryId}/{userId}")
    public ResponseEntity closeEntry(@PathVariable int entryId, @PathVariable int userId) {
        ResponseModel response = this.CommCommodityEntryS.closeEntry(entryId, userId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping(value = "delete/{entryId}")
    public ResponseEntity delete(@PathVariable int entryId) {
        ResponseModel response = this.CommCommodityEntryS.delete(entryId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "originList/{countryId}/{customerId}")
    public ResponseEntity originList( @PathVariable int countryId, @PathVariable int customerId) {
        ResponseModel response = this.CommCommodityEntryS.originList(countryId,customerId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "originTypeList/{origin}/{countryId}/{customerId}")
    public ResponseEntity originTypeList(@PathVariable String origin, @PathVariable int countryId, @PathVariable int customerId) {
        ResponseModel response = this.CommCommodityEntryS.originTypeList(origin,countryId,customerId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "listByUserId/{userId}")
    public ResponseEntity listByUserId(@PathVariable int userId) {
        ResponseModel response = this.CommCommodityEntryS.listByUserId(userId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "list")
    public ResponseEntity list() {
        ResponseModel response = this.CommCommodityEntryS.list();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "sapList/{sapId}")
    public ResponseEntity sapList(@PathVariable int sapId) {
        ResponseModel response = this.CommCommodityEntryS.sapList(sapId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "listActive")
    public ResponseEntity listActive() {
        ResponseModel response = this.CommCommodityEntryS.listActive();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "listActiveEntry")
    public ResponseEntity listActiveEntry() {
        ResponseModel response = this.CommCommodityEntryS.listActiveEntry();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "listApproved")
    public ResponseEntity listApproved() {
        ResponseModel response = this.CommCommodityEntryS.listApproved();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value = "v1/entrada/prealerta")
    public ResponseEntity listCom(@RequestBody ComCommodityEntryPreAlertTCPIPModel b) {
        ResponseModel response = this.CommCommodityEntryS.carguePreAlerta(b);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping(value = "approvedEntry/{entryId}/{userId}")
    public ResponseEntity approvedEntry(@PathVariable int entryId, @PathVariable int userId) {
        ResponseModel response = this.CommCommodityEntryS.approvedEntry(entryId, userId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "listToSap/{customerId}")
    public ResponseEntity listToSap(@PathVariable int customerId) {
        ResponseModel response = this.CommCommodityEntryS.listToSap(customerId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "generatePallet/{codeEntry}")
    public ResponseEntity generatePallet(@PathVariable String codeEntry) {
        ResponseModel response = this.CommCommodityEntryS.generatePallet(codeEntry);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "findByPallet/{entrySapB1Id}")
    public ResponseEntity findByPallet(@PathVariable int entrySapB1Id) {
        ResponseModel response = this.CommCommodityEntryS.findByPallet(entrySapB1Id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
