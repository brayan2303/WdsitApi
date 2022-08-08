package net.woden.wdsit.controller;

import net.woden.wdsit.entity.MeeMeetingEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.MeeMeetingService;
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
@RequestMapping("meeMeeting/")
public class MeeMeetingController {
    @Autowired
    private MeeMeetingService meeMeetingS;
    
    @PostMapping("create")
    public ResponseEntity create(@RequestBody MeeMeetingEntity m){
        ResponseModel response=this.meeMeetingS.create(m);
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping("update")
    public ResponseEntity update(@RequestBody MeeMeetingEntity m){
        ResponseModel response=this.meeMeetingS.update(m);
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity delete(@PathVariable int id){
        ResponseModel response=this.meeMeetingS.delete(id);
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping("list/{startDate}/{endDate}/{groupId}")
    public ResponseEntity list(@PathVariable String startDate,@PathVariable String endDate,@PathVariable String groupId){
        ResponseModel response=this.meeMeetingS.list(startDate,endDate, groupId);
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
     @GetMapping(value = "listGroup/{groupId}/{userId}")
    public ResponseEntity listGroup(@PathVariable int groupId, @PathVariable int userId) {
        ResponseModel response = this.meeMeetingS.listGroup(groupId, userId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
     @GetMapping(value = "listByUser/{userId}")
    public ResponseEntity listByUser(@PathVariable String userId) {
        ResponseModel response = this.meeMeetingS.listByUser(userId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
