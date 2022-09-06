package com.stuent.dpply.api.auth.service;

import com.stuent.dpply.api.auth.domain.dto.DodamLoginDto;
import com.stuent.dpply.api.auth.domain.ro.LoginRo;

public interface AuthService {
    LoginRo dodamLogin(DodamLoginDto dto);
}
