package com.huli.todoapp.service;

import com.huli.todoapp.model.DTO.TodoDto;
import com.huli.todoapp.model.entity.Todo;

import java.util.List;

public interface TodoService {
    TodoDto addTodo(TodoDto todoDto, String jwt);

    List<TodoDto> getAll( String jwt);
    TodoDto update (Long id, String jwt, TodoDto todoDto);
    TodoDto delete (Long id,String jwt);

}
