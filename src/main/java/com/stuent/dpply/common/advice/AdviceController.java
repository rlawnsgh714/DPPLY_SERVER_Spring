package com.stuent.dpply.common.advice;

import com.stuent.dpply.common.exception.*;
import com.stuent.dpply.common.response.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdviceController {

    @ExceptionHandler({CustomException.class})
    protected ResponseEntity<ResponseError> CustomException(CustomException customException){
        final ResponseError responseError = ResponseError.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message(customException.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseError);
    }
}
