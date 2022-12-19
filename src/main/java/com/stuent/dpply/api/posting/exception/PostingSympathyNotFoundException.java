package com.stuent.dpply.api.posting.exception;

import com.stuent.dpply.api.posting.error.PostingErrorCode;
import com.stuent.dpply.common.exception.CustomException;

public class PostingSympathyNotFoundException extends CustomException {

    private PostingSympathyNotFoundException() {
        super(PostingErrorCode.POSTING_SYMPATHY_NOT_FOUND);
    }

    public static final CustomException EXCEPTION = new PostingSympathyNotFoundException();
}
