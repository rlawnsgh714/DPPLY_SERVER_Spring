package com.stuent.dpply.common.exception.error;

import com.stuent.dpply.common.exception.CustomException;

public class InternalServerException extends CustomException {

    private InternalServerException() {
        super(GlobalErrorCode.INTERNAL_SERVER);
    }

    public static final CustomException EXCEPTION = new InternalServerException();
}
