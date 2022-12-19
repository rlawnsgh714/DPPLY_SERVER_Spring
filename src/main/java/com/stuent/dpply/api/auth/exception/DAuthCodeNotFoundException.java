package com.stuent.dpply.api.auth.exception;

import com.stuent.dpply.api.auth.error.AuthErrorCode;
import com.stuent.dpply.common.exception.CustomException;

public class DAuthCodeNotFoundException extends CustomException {

    private DAuthCodeNotFoundException() {
        super(AuthErrorCode.DAUTH_CODE_NOT_FOUND);
    }

    public static final CustomException EXCEPTION = new DAuthCodeNotFoundException();
}
