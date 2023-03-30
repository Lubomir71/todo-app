package com.huli.todoapp.service;

import com.huli.todoapp.model.DTO.TodoDto;
import com.huli.todoapp.repository.TodoRepository;
import com.huli.todoapp.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class TodoServiceImpl implements TodoService{

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    public TodoServiceImpl(TodoRepository todoRepository, UserRepository userRepository) {
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
    }

    @Override
    public TodoDto addTodo(TodoDto todoDto) {
        return null;
    }
}
