package com.stuent.dpply.api.token.exception;

import com.stuent.dpply.api.token.error.TokenErrorCode;
import com.stuent.dpply.common.exception.CustomException;

public class TokenNotFoundException extends CustomException {

    private TokenNotFoundException() {
        super(TokenErrorCode.TOKEN_NOT_FOUND);
    }

    public static final CustomException EXCEPTION = new TokenNotFoundException();
}

