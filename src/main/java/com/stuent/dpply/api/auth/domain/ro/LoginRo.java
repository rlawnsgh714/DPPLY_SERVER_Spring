package com.stuent.dpply.api.auth.domain.ro;

import com.stuent.dpply.api.auth.domain.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRo {

    private User auth;
    private String token;
    private String refreshToken;

    @Builder
    public LoginRo(User auth, String token, String refreshToken) {
        this.auth = auth;
        this.token = token;
        this.refreshToken = refreshToken;
    }

}