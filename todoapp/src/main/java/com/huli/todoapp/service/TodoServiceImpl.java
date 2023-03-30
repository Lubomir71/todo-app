package com.huli.todoapp.service;

import com.huli.todoapp.model.DTO.TodoDto;
import com.huli.todoapp.model.entity.Todo;
import com.huli.todoapp.repository.TodoRepository;
import com.huli.todoapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {

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
        Todo todo = new Todo(todoDto.getDescription(), todoDto.getDone(), decodeJWT.decodeUser(jwt));
        todoRepository.save(todo);
        return new TodoDto(todo);
    }

    @Override
    public List<TodoDto> getAll(String jwt) {
        List<TodoDto> todoDtos = new ArrayList<>();
        List<Todo> todos = todoRepository.getAllByUser(decodeJWT.decodeUser(jwt));
        for (Todo todo : todos) {
            todoDtos.add(new TodoDto(todo));
        }
        return todoDtos;
    }

    @Override
    public TodoDto update(Long id, String jwt, TodoDto todoDto) {
        Todo todo = todoRepository.getTodoById(id);
        if (todo == null)
            throw new IllegalArgumentException("Todo with id: " + id + " does not exist!");
        if (todo.getUser() != decodeJWT.decodeUser(jwt))
            throw new IllegalArgumentException("Todo with id: " + id + " is not your todo!");
        todo.setDescription(todoDto.getDescription());
        todo.setDone(todoDto.getDone());
        todoRepository.save(todo);
        return new TodoDto(todo);
    }

    @Override
    public TodoDto delete(Long id, String jwt) {
        Todo todo = todoRepository.getTodoById(id);
        if (todo == null)
            throw new IllegalArgumentException("Todo with id: " + id + " does not exist!");
        if (todo.getUser() != decodeJWT.decodeUser(jwt))
            throw new IllegalArgumentException("Todo with id: " + id + " is not your todo!");
        todoRepository.delete(todo);
        return new TodoDto(todo);
    }
}
