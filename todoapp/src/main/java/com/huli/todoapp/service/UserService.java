package com.huli.todoapp.service;

import com.huli.todoapp.model.DTO.LoginDto;
import com.huli.todoapp.model.DTO.UserDto;

public interface UserService {
    UserDto userRegistration (UserDto userDto);
    String createToken (LoginDto loginDto);
}
