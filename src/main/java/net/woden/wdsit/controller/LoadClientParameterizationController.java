/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.woden.wdsit.controller;


import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.LoadClientParameterizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author b.algecira
 */
@RestController
@RequestMapping("LoadClientParameterizationS/")
public class LoadClientParameterizationController {

    @Autowired
    private LoadClientParameterizationService LoadClientParameterizationS;

    @GetMapping(value = "create/{id}/{customerId}")
    public ResponseEntity create(@PathVariable int id, @PathVariable int customerId) {
        ResponseModel response = this.LoadClientParameterizationS.create(id, customerId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "createClaro/{id}/{customerId}")
    public ResponseEntity createClaro(@PathVariable int id, @PathVariable int customerId) {
        ResponseModel response = this.LoadClientParameterizationS.createClaro(id, customerId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "createTigo/{id}/{customerId}")
    public ResponseEntity createTigo(@PathVariable int id, @PathVariable int customerId) {
        ResponseModel response = this.LoadClientParameterizationS.createTigo(id, customerId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "createEtb/{id}/{customerId}")
    public ResponseEntity createEtb(@PathVariable int id, @PathVariable int customerId) {
        ResponseModel response = this.LoadClientParameterizationS.createEtb(id, customerId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "createDirectv/{id}/{customerId}")
    public ResponseEntity createDirectv(@PathVariable int id, @PathVariable int customerId) {
        ResponseModel response = this.LoadClientParameterizationS.createDirectv(id, customerId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "createHughes/{id}/{customerId}")
    public ResponseEntity createHughes(@PathVariable int id, @PathVariable int customerId) {
        ResponseModel response = this.LoadClientParameterizationS.createHughes(id, customerId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "createPlataformaMovil/{id}/{customerId}")
    public ResponseEntity createPlataformaMovil(@PathVariable int id, @PathVariable int customerId) {
        ResponseModel response = this.LoadClientParameterizationS.createPlataformaMovil(id, customerId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "createRedExterna/{id}/{customerId}")
    public ResponseEntity createRedExterna(@PathVariable int id, @PathVariable int customerId) {
        ResponseModel response = this.LoadClientParameterizationS.createRedExterna(id, customerId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value = "loadFile/{customerId}/{id}/{creationDate}") // servicio carga de archivos al servidor
    public ResponseEntity loadFile(@PathVariable int customerId, @PathVariable int id, @PathVariable String creationDate, @RequestParam("files") MultipartFile[] files) {
        ResponseModel response = this.LoadClientParameterizationS.loadFile(customerId, id, creationDate, files);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @PostMapping(value = "loadFileLog/{customerId}/{id}/{creationDate}") // servicio carga de archivos al servidor
    public ResponseEntity loadFileLog(@PathVariable int customerId, @PathVariable int id, @PathVariable String creationDate, @RequestParam("files") MultipartFile[] files) {
        ResponseModel response = this.LoadClientParameterizationS.loadFileLog(customerId, id, creationDate, files);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "load/{customerId}")
    public ResponseEntity load(@PathVariable int customerId) {
        ResponseModel response = this.LoadClientParameterizationS.load(customerId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "loadClaro/{customerId}")
    public ResponseEntity loadClaro(@PathVariable int customerId) {
        ResponseModel response = this.LoadClientParameterizationS.loadClaro(customerId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "loadTigo/{customerId}")
    public ResponseEntity loadTigo(@PathVariable int customerId) {
        ResponseModel response = this.LoadClientParameterizationS.loadTigo(customerId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "loadEtb/{customerId}")
    public ResponseEntity loadEtb(@PathVariable int customerId) {
        ResponseModel response = this.LoadClientParameterizationS.loadEtb(customerId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "loadDirectv/{customerId}")
    public ResponseEntity loadDirectv(@PathVariable int customerId) {
        ResponseModel response = this.LoadClientParameterizationS.loadDirectv(customerId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "loadHughes/{customerId}")
    public ResponseEntity loadHughes(@PathVariable int customerId) {
        ResponseModel response = this.LoadClientParameterizationS.loadHughes(customerId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "loadPlataformaMovil/{customerId}")
    public ResponseEntity loadPlataformaMovil(@PathVariable int customerId) {
        ResponseModel response = this.LoadClientParameterizationS.loadPlataformaMovil(customerId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "loadRedExterna/{customerId}")
    public ResponseEntity loadRedExterna(@PathVariable int customerId) {
        ResponseModel response = this.LoadClientParameterizationS.loadRedExterna(customerId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "findByClaro")
    public ResponseEntity findByClaro() {
        ResponseModel response = this.LoadClientParameterizationS.findByClaro();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "findByTigo")
    public ResponseEntity findByTigo() {
        ResponseModel response = this.LoadClientParameterizationS.findByTigo();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "findByEtb")
    public ResponseEntity findByEtb() {
        ResponseModel response = this.LoadClientParameterizationS.findByEtb();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "findByDirectv")
    public ResponseEntity findByDirectv() {
        ResponseModel response = this.LoadClientParameterizationS.findByDirectv();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "findByHughes")
    public ResponseEntity findByHughes() {
        ResponseModel response = this.LoadClientParameterizationS.findByHughes();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "findByPlatafomaMovil")
    public ResponseEntity findByPlatafomaMovil() {
        ResponseModel response = this.LoadClientParameterizationS.findByPlatafomaMovil();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "findByRedExterna")
    public ResponseEntity findByRedExterna() {
        ResponseModel response = this.LoadClientParameterizationS.findByRedExterna();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
     @GetMapping(value = "claroCount")
    public ResponseEntity claroCount() {
        ResponseModel response = this.LoadClientParameterizationS.claroCount();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
     @GetMapping(value = "tigoCount")
    public ResponseEntity tigoCount() {
        ResponseModel response = this.LoadClientParameterizationS.tigoCount();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
     @GetMapping(value = "etbCount")
    public ResponseEntity etbCount() {
        ResponseModel response = this.LoadClientParameterizationS.etbCount();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
     @GetMapping(value = "directvCount")
    public ResponseEntity directvCount() {
        ResponseModel response = this.LoadClientParameterizationS.directvCount();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
     @GetMapping(value = "plataformaMovilCount")
    public ResponseEntity plataformaMovilCount() {
        ResponseModel response = this.LoadClientParameterizationS.plataformaMovilCount();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
     @GetMapping(value = "redExternaCount")
    public ResponseEntity redExternaCount() {
        ResponseModel response = this.LoadClientParameterizationS.redExternaCount();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
     @GetMapping(value = "hughesCount")
    public ResponseEntity hughesCount() {
        ResponseModel response = this.LoadClientParameterizationS.hughesCount();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
     @DeleteMapping(value = "deleteClaro/{id}") //servicio eliminar
    public ResponseEntity deleteClaro(@PathVariable int id) {
        ResponseModel response = this.LoadClientParameterizationS.deleteClaro(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
     @DeleteMapping(value = "deleteTigo/{id}") //servicio eliminar
    public ResponseEntity deleteTigo(@PathVariable int id) {
        ResponseModel response = this.LoadClientParameterizationS.deleteTigo(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
     @DeleteMapping(value = "deleteEtb/{id}") //servicio eliminar
    public ResponseEntity deleteEtb(@PathVariable int id) {
        ResponseModel response = this.LoadClientParameterizationS.deleteEtb(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
     @DeleteMapping(value = "deleteDirectv/{id}") //servicio eliminar
    public ResponseEntity deleteDirectv(@PathVariable int id) {
        ResponseModel response = this.LoadClientParameterizationS.deleteDirectv(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
     @DeleteMapping(value = "deletePlataformaMovil/{id}") //servicio eliminar
    public ResponseEntity deletePlataformaMovil(@PathVariable int id) {
        ResponseModel response = this.LoadClientParameterizationS.deletePlataformaMovil(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
     @DeleteMapping(value = "deleteRedExterna/{id}") //servicio eliminar
    public ResponseEntity deleteRedExterna(@PathVariable int id) {
        ResponseModel response = this.LoadClientParameterizationS.deleteRedExterna(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
     @DeleteMapping(value = "deleteHughes/{id}") //servicio eliminar
    public ResponseEntity deleteHughes(@PathVariable int id) {
        ResponseModel response = this.LoadClientParameterizationS.deleteHughes(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
     @GetMapping(value = "claroList")
    public ResponseEntity claroList() {
        ResponseModel response = this.LoadClientParameterizationS.claroList();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
      @GetMapping(value = "tigoList")
    public ResponseEntity tigoList() {
        ResponseModel response = this.LoadClientParameterizationS.tigoList();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
      @GetMapping(value = "etbList")
    public ResponseEntity etbList() {
        ResponseModel response = this.LoadClientParameterizationS.etbList();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
      @GetMapping(value = "directvList")
    public ResponseEntity directvList() {
        ResponseModel response = this.LoadClientParameterizationS.directvList();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
      @GetMapping(value = "plataformaMovilList")
    public ResponseEntity plataformaMovilList() {
        ResponseModel response = this.LoadClientParameterizationS.plataformaMovilList();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
      @GetMapping(value = "redExternaList")
    public ResponseEntity redExternaList() {
        ResponseModel response = this.LoadClientParameterizationS.redExternaList();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
      @GetMapping(value = "hughesList")
    public ResponseEntity hughesList() {
        ResponseModel response = this.LoadClientParameterizationS.hughesList();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @DeleteMapping(value = "deleteFiles/{customerId}")
    public ResponseEntity deleteFiles(@PathVariable int customerId) {
        ResponseModel response = this.LoadClientParameterizationS.deleteFiles(customerId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @PostMapping(value = "createLogIq/{userId}/{status}") // servicio crear y almacenar
    public ResponseEntity createLogIq(@PathVariable int userId, @PathVariable String status) {
        ResponseModel response = this.LoadClientParameterizationS.createLogIq(userId, status);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PostMapping(value = "createLogDoc/{userId}") // servicio crear y almacenar
    public ResponseEntity createLogDoc(@PathVariable int userId) {
        ResponseModel response = this.LoadClientParameterizationS.createLogDoc(userId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    

}
