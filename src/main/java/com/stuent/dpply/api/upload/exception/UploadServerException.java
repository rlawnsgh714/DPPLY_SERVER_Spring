package com.stuent.dpply.api.upload.exception;

import com.stuent.dpply.api.upload.error.UploadErrorCode;
import com.stuent.dpply.common.exception.CustomException;

public class UploadServerException extends CustomException {

    private UploadServerException() {
        super(UploadErrorCode.UPLOAD_ERROR);
    }

    public static final CustomException EXCEPTION = new UploadServerException();
}
