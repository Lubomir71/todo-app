package com.huli.todoapp.controller;

import com.huli.todoapp.model.DTO.TodoDto;
import com.huli.todoapp.service.TodoService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping("/add")
    public ResponseEntity<TodoDto> todoAdd(@RequestBody TodoDto todoDto,
                                           HttpServletRequest request){
        String jwt = request.getHeader("Authorization").substring(7);
        return new ResponseEntity<>(todoService.addTodo(todoDto, jwt), HttpStatus.CREATED);
    }
}
