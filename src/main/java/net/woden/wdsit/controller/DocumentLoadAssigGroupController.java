package net.woden.wdsit.controller;


import net.woden.wdsit.entity.DocumentLoadAssigGroupEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.DocumentLoadAssigGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author f.casallas
 */
@RestController
@RequestMapping("DocumentLoadAssig/")

public class DocumentLoadAssigGroupController {
     
    @Autowired
    private DocumentLoadAssigGroupService DocumentAssigGroup;

    @PostMapping(value = "create") // servicio crear y almacenar
    public ResponseEntity create(@RequestBody DocumentLoadAssigGroupEntity d) {
        ResponseModel response = this.DocumentAssigGroup.create(d);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value = "delete/{userId}/{groupId}")
    public ResponseEntity delete(@PathVariable int userId, @PathVariable int groupId) {
        ResponseModel response = this.DocumentAssigGroup.delete(userId, groupId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "list") 
    public ResponseEntity list() {
        ResponseModel response = this.DocumentAssigGroup.list();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @GetMapping(value="findAll/{id}")
    public ResponseEntity findAll(@PathVariable int id){
        ResponseModel response=this.DocumentAssigGroup.findAll(id); 
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
