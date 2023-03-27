package com.huli.todoapp.repository;

import com.huli.todoapp.model.DTO.UserDto;
import com.huli.todoapp.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsername(String username);
    User findUserByEmail(String email);
    User save (User user);
}
