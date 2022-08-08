package net.woden.wdsit.controller;

import net.woden.wdsit.entity.DocumentTypeEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.DocumentTypeService;
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
@RequestMapping("DocumentType/")
public class DocumentTypeController {
    
    @Autowired
    private DocumentTypeService DocumentType;

   @PostMapping(value = "create") // servicio crear y almacenar
    public ResponseEntity create(@RequestBody DocumentTypeEntity d) {
        ResponseModel response = this.DocumentType.create(d);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping(value = "update/{updateUserId}")
    public ResponseEntity update(@PathVariable int updateUserId  , @RequestBody DocumentTypeEntity d) {
        ResponseModel response = this.DocumentType.update(updateUserId, d);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping(value = "delete/{id}")
    public ResponseEntity delete(@PathVariable int id) {
        ResponseModel response = this.DocumentType.delete(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "list")
    public ResponseEntity list() {
        ResponseModel response = this.DocumentType.list();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @GetMapping(value = "listByLevelAccess/{userId}")
    public ResponseEntity list(@PathVariable int userId) {
        ResponseModel response = this.DocumentType.listByLevelAccess(userId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "findById/{id}")
    public ResponseEntity findById(@PathVariable int id) {
        ResponseModel response = this.DocumentType.findById(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
