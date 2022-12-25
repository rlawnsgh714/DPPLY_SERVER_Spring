package com.stuent.dpply.common.exception.error;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum GlobalErrorCode implements ErrorProperty {


    INTERNAL_SERVER(500,"서버 에러"),
    TOKEN_EXPIRED(400, "토큰 만료")
    ;

    private final int status;
    private final String message;
}