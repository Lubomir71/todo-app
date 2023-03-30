package com.huli.todoapp.model.DTO;

import com.huli.todoapp.model.entity.Todo;

public class TodoDto {
    private Long id;
    private String description;
    private Boolean done = false;

    public TodoDto() {
    }

    public TodoDto(String description) {
        this.description = description;
    }

    public TodoDto(Todo todo) {
        this.id = todo.getId();
        this.description = todo.getDescription();
        this.done = todo.getDone();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }
}


