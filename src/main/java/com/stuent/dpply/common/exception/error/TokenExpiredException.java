package com.stuent.dpply.common.exception.error;

import com.stuent.dpply.common.exception.CustomException;

public class TokenExpiredException extends CustomException {

    private TokenExpiredException() {
        super(GlobalErrorCode.TOKEN_EXPIRED);
    }

    public static final CustomException EXCEPTION = new TokenExpiredException();
}

