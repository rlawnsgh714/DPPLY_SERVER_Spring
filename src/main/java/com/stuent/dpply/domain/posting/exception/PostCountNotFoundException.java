package com.stuent.dpply.domain.posting.exception;

import com.stuent.dpply.domain.posting.error.PostingErrorCode;
import com.stuent.dpply.common.exception.CustomException;

public class PostCountNotFoundException extends CustomException {

    private PostCountNotFoundException() {
        super(PostingErrorCode.POST_COUNT_NOT_FOUND);
    }

    public static final CustomException EXCEPTION = new PostCountNotFoundException();
}
