package com.huli.todoapp.controller;

import com.huli.todoapp.model.DTO.TodoDto;
import com.huli.todoapp.service.TodoService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/get")
    public ResponseEntity<List<TodoDto>> todoGetAll(HttpServletRequest request){
        String jwt = request.getHeader("Authorization").substring(7);
        return new ResponseEntity<>(todoService.getAll(jwt), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TodoDto> todoUpdate(@PathVariable Long id,
                                                    @RequestBody TodoDto todoDto,
                                                    HttpServletRequest request){
        String jwt = request.getHeader("Authorization").substring(7);
        return new ResponseEntity<>(todoService.update(id,jwt, todoDto),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<TodoDto> todoDelete(@PathVariable Long id,
                                              HttpServletRequest request){
        String jwt = request.getHeader("Authorization").substring(7);
        return new ResponseEntity<>(todoService.delete(id,jwt),HttpStatus.OK);
    }
}
