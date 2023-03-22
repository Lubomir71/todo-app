package com.huli.todoapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @PostMapping("/registration")
    public ResponseEntity<Object> registration(){
        return new ResponseEntity<>((HttpStatus.OK));
    }
}
