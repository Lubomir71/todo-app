package com.huli.todoapp.model.DTO;

public class TodoDto {
    private Long id;
    private String description;
    private Boolean done = false;

    public TodoDto() {
    }

    public TodoDto(String description) {
        this.description = description;
    }

    public TodoDto(Long id, String description, Boolean done) {
        this.id = id;
        this.description = description;
        this.done = done;
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


