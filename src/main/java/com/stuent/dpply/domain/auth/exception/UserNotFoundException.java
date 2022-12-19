package com.stuent.dpply.domain.auth.exception;

import com.stuent.dpply.domain.auth.error.AuthErrorCode;
import com.stuent.dpply.common.exception.CustomException;

public class UserNotFoundException extends CustomException {

    private UserNotFoundException() {
        super(AuthErrorCode.USER_NOT_FOUND);
    }

    public static final CustomException EXCEPTION = new UserNotFoundException();
}

