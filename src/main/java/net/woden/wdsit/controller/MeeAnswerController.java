package net.woden.wdsit.controller;

import java.util.ArrayList;
import net.woden.wdsit.entity.MeeAnswerEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.MeeAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("meeAnswer/")
public class MeeAnswerController {
    @Autowired
    private MeeAnswerService meeAnswerS;
    
    @PostMapping("create")
    public ResponseEntity create(@RequestBody ArrayList<MeeAnswerEntity>array){
        ResponseModel response=this.meeAnswerS.create(array);
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping("list/{meetingId}")
    public ResponseEntity list(@PathVariable int meetingId){
        ResponseModel response=this.meeAnswerS.list(meetingId);
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping("listByUserId/{meetingId}/{periodicity}/{answerDate}/{userId}")
    public ResponseEntity listByUserId(@PathVariable int meetingId,@PathVariable String periodicity,@PathVariable String answerDate,@PathVariable int userId){
        ResponseModel response=this.meeAnswerS.listByUserId(meetingId,periodicity,answerDate, userId);
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @GetMapping("listWeek/{meetingId}/{userId}")
    public ResponseEntity listWeek(@PathVariable int meetingId,@PathVariable int userId){
        ResponseModel response=this.meeAnswerS.listWeek(meetingId,userId);
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping("update")
    public ResponseEntity update(@RequestBody MeeAnswerEntity m){
        ResponseModel response=this.meeAnswerS.update(m);
        return new ResponseEntity(response,response.getStatusCode()==200?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
