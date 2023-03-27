package com.huli.todoapp.service;

import com.huli.todoapp.model.DTO.UserDto;
import com.huli.todoapp.model.entity.User;
import com.huli.todoapp.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto userRegistration(UserDto userDto) {
        if (userRepository.findUserByUsername(userDto.getUsername())!=null){
            throw new IllegalArgumentException("Username already exists!");
        }
        if (userRepository.findUserByEmail(userDto.getEmail())!=null){
            throw new IllegalArgumentException("Email already exists!");
        }
        User user = userRepository.save(new User(userDto.getUsername(),
                passwordEncoder.encode(userDto.getPassword()),
                userDto.getEmail(),
                "user"));
        return new UserDto(user.getId(),user.getUsername(),"protected",user.getEmail());
    }
}
