package com.stuent.dpply.domain.upload.error;

import com.stuent.dpply.common.exception.error.ErrorProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum UploadErrorCode implements ErrorProperty {

    UPLOAD_ERROR(500, "서버 오류로 인한 업로드 실패")
    ;

    private final int status;
    private final String message;
}
