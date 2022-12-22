package com.stuent.dpply.domain.posting.exception;

import com.stuent.dpply.common.exception.CustomException;
import com.stuent.dpply.domain.posting.error.PostingErrorCode;

public class MethodNotFoundException extends CustomException {

    private MethodNotFoundException() {
        super(PostingErrorCode.METHOD_IS_NOT_FOUND);
    }

    public static final CustomException EXCEPTION = new MethodNotFoundException();
}

