package com.huli.todoapp.service;

import com.huli.todoapp.model.DTO.TodoDto;

public interface TodoService {
    TodoDto addTodo(TodoDto todoDto, String jwt);
}
