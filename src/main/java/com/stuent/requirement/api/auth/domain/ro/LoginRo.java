package com.stuent.requirement.api.auth.domain.ro;

import com.stuent.requirement.api.auth.domain.entity.Auth;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRo {

    private Auth auth;
    private String token;
    private String refreshToken;

    @Builder
    public LoginRo(Auth auth, String token, String refreshToken) {
        this.auth = auth;
        this.token = token;
        this.refreshToken = refreshToken;
    }

}