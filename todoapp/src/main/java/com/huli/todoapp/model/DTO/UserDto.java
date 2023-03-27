package com.huli.todoapp.model.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class UserDto {
    private Long id;
    @NotBlank(message = "- given name cannot be null or empty!")
    private String username;
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8}$",
            message = "- must have at least 1 digit, 1 [a-z], 1[A-Z], 1 special character, total 8 characters")
    private String password;
    @Email(message = "- not valid email address!")
    private String email;

    public UserDto(){
    }

    public UserDto(Long id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }
    public Long getId() {return id; }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
