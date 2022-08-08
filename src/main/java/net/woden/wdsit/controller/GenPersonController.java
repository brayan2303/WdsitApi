package net.woden.wdsit.controller;

import net.woden.wdsit.entity.GenPersonEntity;
import net.woden.wdsit.model.GenPersonUpdatePasswordModel;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.model.ResponseTokenModel;
import net.woden.wdsit.service.GenPersonService;
import net.woden.wdsit.util.JwtGenerateToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("genPerson/")
public class GenPersonController {

    @Autowired
    private GenPersonService genPersonS;
    @Autowired
    private JwtGenerateToken jwtGenerator;

    @PostMapping(value = "create")
    public ResponseEntity create(@RequestBody GenPersonEntity g) {
        ResponseModel response = this.genPersonS.create(g);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping(value = "update")
    public ResponseEntity update(@RequestBody GenPersonEntity g) {
        ResponseModel response = this.genPersonS.update(g);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping(value = "updatePassword/{id}")
    public ResponseEntity updatePassword(@PathVariable int id, @RequestBody GenPersonUpdatePasswordModel passModel) {
        ResponseModel response = this.genPersonS.updatePassword(id, passModel);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping(value = "delete/{genPersonId}/{active}")
    public ResponseEntity delete(@PathVariable int genPersonId, @PathVariable boolean active) {
        ResponseModel response = this.genPersonS.delete(genPersonId, active);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value = "login")
    public ResponseEntity login(@RequestParam String userName, @RequestParam String password, @RequestParam String country) {
        ResponseTokenModel response = this.genPersonS.login(userName, password, country);
        if (response.getObject() != null) {
            response.setToken(this.jwtGenerator.getToken((GenPersonEntity) response.getObject()));
        }
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "findById/{id}")
    public ResponseEntity findById(@PathVariable int id) {
        ResponseModel response = this.genPersonS.findById(id);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "findByIdentification/{identification}")
    public ResponseEntity findByIdentification(@PathVariable int identification) {
        ResponseModel response = this.genPersonS.findByIdentification(identification);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "findByPosition/{position}")
    public ResponseEntity findByPosition(@PathVariable String position) {
        ResponseModel response = this.genPersonS.findByPosition(position);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "list")
    public ResponseEntity list() {
        ResponseModel response = this.genPersonS.list();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "listDirector")
    public ResponseEntity listDirector() {
        ResponseModel response = this.genPersonS.listDirector();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "findImage/{personId}")
    public ResponseEntity findImage(@PathVariable int personId) {
        ResponseModel response = this.genPersonS.findImage(personId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value = "loadImage/{personId}")
    public ResponseEntity loadImage(@PathVariable int personId, @RequestParam("image") MultipartFile image) {
        ResponseModel response = this.genPersonS.loadImage(personId, image);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value = "logOut")
    public ResponseEntity logOut() {
        ResponseModel response = new ResponseModel();
        response.setStatusCode(200);
        response.setMessage("OK");
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "updatePasswords")
    public ResponseEntity updatePasswords() {
        ResponseModel response = this.genPersonS.updatePasswords();
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "listCountry/{countryId}")
    public ResponseEntity listCountry(@PathVariable int countryId) {
        ResponseModel response = this.genPersonS.listCountry(countryId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
