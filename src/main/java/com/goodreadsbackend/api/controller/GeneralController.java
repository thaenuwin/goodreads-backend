/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.goodreadsbackend.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author thaenuwin
 */

@RestController
public class GeneralController {



    @GetMapping(path = "/alive")
    public ResponseEntity<Long> alive() {
        return ResponseEntity.ok(System.currentTimeMillis());
    }

}
