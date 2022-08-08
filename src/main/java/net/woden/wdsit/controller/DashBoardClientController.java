package net.woden.wdsit.controller;

import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.DashBoardClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author f.casallas
 */
@RestController
@RequestMapping("DashBoardClient/")
public class DashBoardClientController {
    @Autowired
    private DashBoardClientService DashBoard;
    
    @GetMapping(value = "FindByCustomerId/{customerId}/{countryId}")
    public ResponseEntity FindByCustomerId(@PathVariable int customerId, @PathVariable int countryId) {
        ResponseModel response = this.DashBoard.FindByCustomerId(customerId, countryId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @GetMapping(value = "listFamily/{plantId}/{familia}/{customerId}/{countryId}")
    public ResponseEntity listFamily(@PathVariable int plantId, @PathVariable String familia, @PathVariable int customerId, @PathVariable int countryId) {
        ResponseModel response = this.DashBoard.listFamily(plantId ,customerId, familia, countryId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
      @GetMapping(value = "findByFamily/{familia}/{customer}/{countryId}")
    public ResponseEntity findByFamily(@PathVariable String familia,@PathVariable String customer, @PathVariable int countryId) {
        ResponseModel response = this.DashBoard.findByFamily(customer, familia, countryId);
        return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
 
}
