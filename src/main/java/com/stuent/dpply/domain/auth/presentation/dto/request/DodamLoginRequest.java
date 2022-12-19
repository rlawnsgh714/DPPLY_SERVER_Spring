package com.stuent.dpply.domain.auth.presentation.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class DodamLoginRequest {

    @NotBlank(message = "code는 필수 입력값입니다")
    private String code;

    public DodamLoginRequest(String code) {
        this.code = code;
    }
}
