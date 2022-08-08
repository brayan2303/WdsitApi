
package net.woden.wdsit.controller;

import net.woden.wdsit.entity.CovFormDayEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.CovFormDayService;
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
@RequestMapping("CovFormDay/")
public class CovFormDayController {
    
    @Autowired
    private CovFormDayService CovFormDay;
    
     @PostMapping(value = "create") // servicio crear y almacenar
    public ResponseEntity create(@RequestBody CovFormDayEntity a) {
        ResponseModel response = this.CovFormDay.create(a);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping(value = "update") //servicio actualizar
    public ResponseEntity update(@RequestBody CovFormDayEntity a) {
        ResponseModel response = this.CovFormDay.update(a);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping(value = "delete/{id}") //servicio eliminar
    public ResponseEntity delete(@PathVariable int id) {
        ResponseModel response = this.CovFormDay.delete(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "list") //servicio mostrar en pantalla
    public ResponseEntity list() {
        ResponseModel response = this.CovFormDay.list();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "findById/{id}") //servicio mostrar en pantalla
    public ResponseEntity findById(@PathVariable int id) {
        ResponseModel response = this.CovFormDay.findById(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

     @PostMapping(value = "loadFile/{identification}/{type}/{creationDate}")
    public ResponseEntity loadFile(@PathVariable int identification,@PathVariable String type, @PathVariable String creationDate, @RequestParam("files") MultipartFile[] files) {
        ResponseModel response = this.CovFormDay.loadFile(identification, type,creationDate, files);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
   @GetMapping(value="listFile/{identification}/{type}/{creationDate}")
    public ResponseEntity listFile(@PathVariable int identification, @PathVariable String type, @PathVariable String creationDate){
        ResponseModel response=this.CovFormDay.listFile(identification,type,creationDate); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value = "activeInactive/{id}/{status}") //servicio actualizar
    public ResponseEntity activeInactive(@PathVariable int id,@PathVariable boolean status) {
        ResponseModel response = this.CovFormDay.activeInactive(id,status);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
