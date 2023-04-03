package com.stuent.dpply.domain.posting.exception;

import com.stuent.dpply.common.exception.CustomException;
import com.stuent.dpply.domain.posting.error.PostingErrorCode;

public class AlreadyPostingSympathyException extends CustomException {

    private AlreadyPostingSympathyException() {
        super(PostingErrorCode.ALREADY_POSTING_SYMPATHY);
    }

    public static final CustomException EXCEPTION = new AlreadyPostingSympathyException();
}
