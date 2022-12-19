package com.stuent.dpply.domain.posting.exception;

import com.stuent.dpply.domain.posting.error.PostingErrorCode;
import com.stuent.dpply.common.exception.CustomException;

public class NotCtrlPostException extends CustomException {

    private NotCtrlPostException() {
        super(PostingErrorCode.NOT_CTRL_POST);
    }

    public static final CustomException EXCEPTION = new NotCtrlPostException();
}
