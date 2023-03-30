package com.huli.todoapp.controller;

import com.huli.todoapp.model.DTO.TodoDto;
import com.huli.todoapp.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/todo")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping("/add")
    public ResponseEntity<TodoDto> todoAdd(@RequestBody TodoDto todoDto){
        return new ResponseEntity<>(todoService.addTodo(todoDto), HttpStatus.CREATED);
    }
}
