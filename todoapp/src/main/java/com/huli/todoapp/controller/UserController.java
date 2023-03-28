package com.huli.todoapp.controller;

import com.huli.todoapp.model.DTO.LoginDto;
import com.huli.todoapp.model.DTO.UserDto;
import com.huli.todoapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public ResponseEntity<UserDto> registration(@RequestBody @Valid UserDto userDto){
        return new ResponseEntity<>(userService.userRegistration(userDto), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto){
        return new ResponseEntity<>(userService.createToken(loginDto),HttpStatus.OK);
    }
}
