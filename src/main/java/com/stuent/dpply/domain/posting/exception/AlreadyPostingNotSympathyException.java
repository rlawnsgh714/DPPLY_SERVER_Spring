package com.stuent.dpply.domain.posting.exception;

import com.stuent.dpply.common.exception.CustomException;
import com.stuent.dpply.domain.posting.error.PostingErrorCode;

public class AlreadyPostingNotSympathyException extends CustomException {

    private AlreadyPostingNotSympathyException() {
        super(PostingErrorCode.ALREADY_POSTING_NOT_SYMPATHY);
    }

    public static final CustomException EXCEPTION = new AlreadyPostingNotSympathyException();
}
