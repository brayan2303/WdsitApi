package net.woden.wdsit.controller;

import net.woden.wdsit.entity.DocumentLoadDownloadEntity;
import net.woden.wdsit.entity.DocumentLoadEntity;
import net.woden.wdsit.model.DocumentFileModel;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.DocumentLoadService;
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

/**
 *
 * @author f.casallas
 */
@RestController
@RequestMapping("DocumentLoad/")
public class DocumentLoadController {

    @Autowired
    private DocumentLoadService DocumentLoad;

    @PostMapping(value = "create/{userPropertyId}/{creationUserId}")
    public ResponseEntity create(@PathVariable int userPropertyId, @PathVariable int creationUserId, @RequestBody DocumentLoadEntity d) {
        ResponseModel response = this.DocumentLoad.create(userPropertyId, creationUserId, d);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping(value = "update/{userId}") //servicio actualizar
    public ResponseEntity update(@PathVariable int userId, @RequestBody DocumentLoadEntity d) {
        ResponseModel response = this.DocumentLoad.update(userId, d);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping(value = "delete/{id}")
    public ResponseEntity delete(@PathVariable int id) {
        ResponseModel response = this.DocumentLoad.delete(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "list")
    public ResponseEntity list() {
        ResponseModel response = this.DocumentLoad.list();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "findByIdentification/{Identification}/{TypeDocument}")
    public ResponseEntity findByIdentification(@PathVariable String Identification, @PathVariable int TypeDocument) {
        ResponseModel response = this.DocumentLoad.findByIdentification(Identification, TypeDocument);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "findById/{id}")
    public ResponseEntity findById(@PathVariable int id) {
        ResponseModel response = this.DocumentLoad.findById(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "findVersion/{identification}/{documentId}")
    public ResponseEntity findVersion(@PathVariable int identification, @PathVariable int documentId) {
        ResponseModel response = this.DocumentLoad.findVersion(identification, documentId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value = "loadFile/{documentId}") // servicio carga de archivos al servidor
    public ResponseEntity loadFile(@PathVariable int documentId, @RequestParam("files") MultipartFile[] files) {
        ResponseModel response = this.DocumentLoad.loadFile(documentId, files);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "listFile/{documentLoadId}")
    public ResponseEntity listFile(@PathVariable int documentLoadId) {
        ResponseModel response = this.DocumentLoad.listFile(documentLoadId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "searchDocs/{documentLoadId}")
    public ResponseEntity listDocs(@PathVariable int documentLoadId) {
        ResponseModel response = this.DocumentLoad.searchDocs(documentLoadId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value = "registerDownload")
    public ResponseEntity registerDownload(@RequestBody DocumentLoadDownloadEntity d) {
        ResponseModel response = this.DocumentLoad.registerDownload(d);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
