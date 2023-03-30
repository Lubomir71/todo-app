package com.huli.todoapp.service;

import com.huli.todoapp.model.DTO.TodoDto;
import com.huli.todoapp.model.entity.Todo;
import com.huli.todoapp.repository.TodoRepository;
import com.huli.todoapp.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class TodoServiceImpl implements TodoService{

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;
    private final DecodeJWT decodeJWT;

    public TodoServiceImpl(TodoRepository todoRepository, UserRepository userRepository, DecodeJWT decodeJWT) {
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
        this.decodeJWT = decodeJWT;
    }

    @Override
    public TodoDto addTodo(TodoDto todoDto, String jwt) {
        Todo todo = new Todo(todoDto.getDescription(),todoDto.getDone(),decodeJWT.decodeUser(jwt));
        todoRepository.save(todo);
        return new TodoDto(todo);
    }
}
