/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.controller;

import net.woden.wdsit.entity.LoadClientPersonHistoryEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.service.LoadClientPersonHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author b.algecira
 */
@RestController
@RequestMapping("LoadClientPersonHistory/")
public class LoadClientPersonHistoryController {

        @Autowired
        private LoadClientPersonHistoryService LoadClientPersonHistory;

        @PostMapping(value = "create") // servicio crear y almacenar
        public ResponseEntity create(@RequestBody LoadClientPersonHistoryEntity l) {
            ResponseModel response = this.LoadClientPersonHistory.create(l);
            return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
        }

        @GetMapping(value = "list") //servicio mostrar en pantalla
        public ResponseEntity list() {
            ResponseModel response = this.LoadClientPersonHistory.list();
            return new ResponseEntity(response, response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

