package com.huli.todoapp.repository;

import com.huli.todoapp.model.entity.Todo;
import com.huli.todoapp.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo,Long> {

    List<Todo> getAllByUser(User user);
    Todo getTodoById(Long id);
}
