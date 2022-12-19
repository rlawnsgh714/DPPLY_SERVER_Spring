package com.stuent.dpply.domain.auth.presentation.dto.response;

import com.stuent.dpply.domain.auth.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {

    private User auth;
    private String token;
    private String refreshToken;

    @Builder
    public LoginResponse(User auth, String token, String refreshToken) {
        this.auth = auth;
        this.token = token;
        this.refreshToken = refreshToken;
    }

}