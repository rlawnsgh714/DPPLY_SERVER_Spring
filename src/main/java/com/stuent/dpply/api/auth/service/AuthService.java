package com.stuent.dpply.api.auth.service;

import com.stuent.dpply.api.auth.domain.dto.DodamLoginDto;
import com.stuent.dpply.api.auth.domain.entity.User;
import com.stuent.dpply.api.auth.domain.ro.LoginRo;

import java.util.List;

public interface AuthService {
    LoginRo dodamLogin(DodamLoginDto dto);

    List<User> getUsers();

    User getUserById(String id);
}
