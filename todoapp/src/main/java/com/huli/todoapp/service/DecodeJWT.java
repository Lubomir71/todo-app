package com.huli.todoapp.service;

import com.huli.todoapp.model.entity.User;

public interface DecodeJWT {
    User decodeUser (String token);
}
