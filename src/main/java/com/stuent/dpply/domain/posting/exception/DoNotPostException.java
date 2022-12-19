package com.stuent.dpply.domain.posting.exception;

import com.stuent.dpply.domain.posting.error.PostingErrorCode;
import com.stuent.dpply.common.exception.CustomException;

public class DoNotPostException extends CustomException {

    private DoNotPostException() {
        super(PostingErrorCode.DO_NOT_POST);
    }

    public static final CustomException EXCEPTION = new DoNotPostException();
}
