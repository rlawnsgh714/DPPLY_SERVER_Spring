package com.stuent.dpply.api.posting.exception;

import com.stuent.dpply.api.posting.error.PostingErrorCode;
import com.stuent.dpply.common.exception.CustomException;

public class NotCtrlPostException extends CustomException {

    private NotCtrlPostException() {
        super(PostingErrorCode.NOT_CTRL_POST);
    }

    public static final CustomException EXCEPTION = new NotCtrlPostException();
}
