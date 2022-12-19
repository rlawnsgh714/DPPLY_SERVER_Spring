package com.stuent.dpply.domain.auth.error;

import com.stuent.dpply.common.exception.error.ErrorProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum AuthErrorCode implements ErrorProperty {


    DAUTH_CODE_NOT_FOUND(403, "변조된 코드입니다"),
    USER_NOT_FOUND(404, "해당 유저는 존재하지 않습니다")
    ;

    private final int status;
    private final String message;
}