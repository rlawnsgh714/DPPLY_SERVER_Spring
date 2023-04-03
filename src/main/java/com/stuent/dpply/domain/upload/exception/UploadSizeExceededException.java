package com.stuent.dpply.domain.upload.exception;

import com.stuent.dpply.common.exception.CustomException;
import com.stuent.dpply.domain.upload.error.UploadErrorCode;

public class UploadSizeExceededException extends CustomException {

    private UploadSizeExceededException() {
        super(UploadErrorCode.UPLOAD_SIZE_EXCEEDED);
    }

    public static final CustomException EXCEPTION = new UploadSizeExceededException();
}
