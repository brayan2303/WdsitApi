package net.woden.wdsit.controller;

import net.woden.wdsit.entity.DocumentLevelAccessEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.DocumentLevelAccessService;
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
 * @author f.casallas
 */
@RestController
@RequestMapping("DocumentLevelAccess/")
public class DocumentLevelAccessController {

    @Autowired
    private DocumentLevelAccessService DocumentLevelAccess;

    @PostMapping(value = "create")
    public ResponseEntity create(@RequestBody DocumentLevelAccessEntity d) {
        ResponseModel response = this.DocumentLevelAccess.create(d);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping(value = "update")
    public ResponseEntity update(@RequestBody DocumentLevelAccessEntity d) {
        ResponseModel response = this.DocumentLevelAccess.update(d);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping(value = "delete/{id}")
    public ResponseEntity delete(@PathVariable int id) {
        ResponseModel response = this.DocumentLevelAccess.delete(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "list")
    public ResponseEntity list() {
        ResponseModel response = this.DocumentLevelAccess.list();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "findById/{id}")
    public ResponseEntity findById(@PathVariable int id) {
        ResponseModel response = this.DocumentLevelAccess.findById(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
