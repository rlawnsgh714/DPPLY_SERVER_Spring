package com.stuent.dpply.api.posting.exception;

import com.stuent.dpply.api.posting.error.PostingErrorCode;
import com.stuent.dpply.common.exception.CustomException;

public class DoNotPostException extends CustomException {

    private DoNotPostException() {
        super(PostingErrorCode.DO_NOT_POST);
    }

    public static final CustomException EXCEPTION = new DoNotPostException();
}
