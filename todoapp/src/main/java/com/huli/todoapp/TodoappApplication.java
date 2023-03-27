package com.huli.todoapp;

import com.huli.todoapp.model.entity.User;
import com.huli.todoapp.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class TodoappApplication implements CommandLineRunner {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public TodoappApplication(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(TodoappApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count()==0){
            userRepository.save(new User("Lubo",
                    passwordEncoder.encode("12345"),
                    "lubo.drab@gmail.com",
                    "admin"));
        }
    }
}
