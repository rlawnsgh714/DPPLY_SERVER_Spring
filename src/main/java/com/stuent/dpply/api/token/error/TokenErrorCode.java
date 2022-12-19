package com.stuent.dpply.api.token.error;

import com.stuent.dpply.common.exception.error.ErrorProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum TokenErrorCode implements ErrorProperty {


    TOKEN_NOT_FOUND(400, "토큰이 없습니다"),
    TOKEN_EXPIRED(410, "토큰 만료되었습니다"),
    TOKEN_SERVER_ERROR(500, "토큰 해석중 에러 발생")
    ;

    private final int status;
    private final String message;
}