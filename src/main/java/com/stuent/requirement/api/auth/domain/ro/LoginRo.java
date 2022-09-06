package com.stuent.requirement.api.auth.domain.ro;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Member;

@Getter
@Setter
public class LoginRo {

    private Member member; //TODO 실제 유저로 바꾸기
    private String token;
    private String refreshToken;

    @Builder
    public LoginRo(Member member, String token, String refreshToken) {
        this.member = member;
        this.token = token;
        this.refreshToken = refreshToken;
    }

}