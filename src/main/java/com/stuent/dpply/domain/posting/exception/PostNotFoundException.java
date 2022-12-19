package com.stuent.dpply.domain.posting.exception;

import com.stuent.dpply.domain.posting.error.PostingErrorCode;
import com.stuent.dpply.common.exception.CustomException;

public class PostNotFoundException extends CustomException {

    private PostNotFoundException() {
        super(PostingErrorCode.POST_NOT_FOUND);
    }

    public static final CustomException EXCEPTION = new PostNotFoundException();
}
