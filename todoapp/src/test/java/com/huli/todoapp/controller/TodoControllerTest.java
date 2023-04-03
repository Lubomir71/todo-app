package com.huli.todoapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.huli.todoapp.TodoappApplication;
import com.huli.todoapp.model.DTO.LoginDto;
import com.huli.todoapp.model.DTO.TodoDto;
import com.huli.todoapp.model.entity.Todo;
import com.huli.todoapp.model.entity.User;
import com.huli.todoapp.repository.TodoRepository;
import com.huli.todoapp.repository.UserRepository;
import com.huli.todoapp.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestPropertySource("/application-test.properties")
@AutoConfigureMockMvc
@SpringBootTest(classes = TodoappApplication.class)
class TodoControllerTest {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TodoRepository todoRepository;
    private final UserService userService;
    private ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    MockMvc mvc;

    private User user;
    private String jwt;

    @Autowired
    TodoControllerTest(PasswordEncoder passwordEncoder, UserRepository userRepository, TodoRepository todoRepository, UserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.todoRepository = todoRepository;
        this.userService = userService;
    }

    @BeforeEach
    public void beforeEach() {
        user = userRepository.findUserByUsername("test");
        if (user == null) {
            user = userRepository.save(new User("test",
                    passwordEncoder.encode("12345"),
                    "user@user.com",
                    "user"));
            jwt = userService.createToken(new LoginDto("test", "12345"));
        }
    }


    @Test
    void todoAddOK() throws Exception {
        TodoDto todoDto = new TodoDto("todoAdd - test");
        mvc.perform(post("/add")
                .header("Authorization", "Bearer " + jwt)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(todoDto)))
                .andDo(print())
                .andExpect(status().isCreated());
        Todo todo = todoRepository.getTopByOrderByIdDesc();
        assertEquals(todo.getDescription(),todoDto.getDescription());
    }

    @Test
    void todoGetAll() {
    }

    @Test
    void todoUpdate() {
    }

    @Test
    void todoDelete() {
    }
}