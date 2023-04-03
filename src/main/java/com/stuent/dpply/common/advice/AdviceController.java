package com.stuent.dpply.common.advice;

import com.stuent.dpply.common.exception.*;
import com.stuent.dpply.common.response.ResponseError;
import com.stuent.dpply.domain.upload.exception.UploadSizeExceededException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@RestControllerAdvice
public class AdviceController {

    @ExceptionHandler({CustomException.class})
    protected ResponseEntity<ResponseError> handleCustomException(CustomException customException) {
        final ResponseError responseError = ResponseError.builder()
                .status(HttpStatus.valueOf(customException.getErrorProperty().getStatus()))
                .message(customException.getErrorProperty().getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.valueOf(customException.getErrorProperty().getStatus())).body(responseError);
    }

    @ExceptionHandler({MaxUploadSizeExceededException.class})
    protected ResponseEntity<ResponseError> handleUploadSizeException(UploadSizeExceededException exception) {
        final ResponseError responseError = ResponseError.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message(exception.getErrorProperty().getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseError);
    }

    @ExceptionHandler({Exception.class})
    protected ResponseEntity<ResponseError> handleException(Exception exception) {
        final ResponseError responseError = ResponseError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(exception.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseError);
    }
}
