package com.stuent.requirement.api.auth.service;

import com.stuent.requirement.api.auth.domain.dto.DodamLoginDto;
import com.stuent.requirement.api.auth.domain.ro.LoginRo;

public interface AuthService {
    LoginRo dodamLogin(DodamLoginDto dto);
}
