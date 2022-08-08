package net.woden.wdsit.controller;

import net.woden.wdsit.entity.TasTaskEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.TasTaskService;
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
@RequestMapping("tasTask/")
public class TasTaskController {

    @Autowired
    private TasTaskService tasTaskS;

    @PostMapping(value = "create")
    public ResponseEntity create(@RequestBody TasTaskEntity t) {
        ResponseModel response = this.tasTaskS.create(t);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping(value = "update")
    public ResponseEntity update(@RequestBody TasTaskEntity t) {
        ResponseModel response = this.tasTaskS.update(t);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping(value = "delete/{taskId}")
    public ResponseEntity delete(@PathVariable int taskId) {
        ResponseModel response = this.tasTaskS.delete(taskId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping(value = "close/{taskId}")
    public ResponseEntity close(@PathVariable int taskId) {
        ResponseModel response = this.tasTaskS.close(taskId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "findById/{taskId}")
    public ResponseEntity findById(@PathVariable int taskId) {
        ResponseModel response = this.tasTaskS.findById(taskId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "list/{assignedPersonId}")
    public ResponseEntity list(@PathVariable int assignedPersonId) {
        ResponseModel response = this.tasTaskS.list(assignedPersonId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "listAll")
    public ResponseEntity listAll() {
        ResponseModel response = this.tasTaskS.listAll();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
