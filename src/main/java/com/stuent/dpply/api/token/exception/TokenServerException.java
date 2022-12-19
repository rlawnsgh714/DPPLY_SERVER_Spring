package com.stuent.dpply.api.token.exception;

import com.stuent.dpply.api.token.error.TokenErrorCode;
import com.stuent.dpply.common.exception.CustomException;

public class TokenServerException extends CustomException {

    private TokenServerException() {
        super(TokenErrorCode.TOKEN_SERVER_ERROR);
    }

    public static final CustomException EXCEPTION = new TokenServerException();
}

