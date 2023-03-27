package com.huli.todoapp.controller;

import com.huli.todoapp.exception.ErrorDTO;
import com.huli.todoapp.model.DTO.UserDto;
import com.huli.todoapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public ResponseEntity<UserDto> registration(@RequestBody @Valid UserDto userDto){
        UserDto response = userService.userRegistration(userDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
