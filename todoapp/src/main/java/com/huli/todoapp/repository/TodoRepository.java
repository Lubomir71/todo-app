package com.huli.todoapp.repository;

import com.huli.todoapp.model.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo,Long> {

}
