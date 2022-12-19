package com.stuent.dpply.domain.auth.service;

import com.stuent.dpply.domain.auth.presentation.dto.request.DodamLoginRequest;
import com.stuent.dpply.domain.auth.entity.User;
import com.stuent.dpply.domain.auth.presentation.dto.response.LoginResponse;

import java.util.List;

public interface AuthService {
    LoginResponse dodamLogin(DodamLoginRequest dto);

    List<User> getUsers();

    User getUserById(String id);
}
