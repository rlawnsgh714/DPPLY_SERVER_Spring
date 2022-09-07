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

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({BadRequestException.class})
    protected ResponseEntity<ResponseError> BadRequestException(BadRequestException badRequestException){
        final ResponseError responseError = ResponseError.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message(badRequestException.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseError);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler({ConflictException.class})
    protected ResponseEntity<ResponseError> ConflictException(ConflictException conflictException){
        final ResponseError responseError = ResponseError.builder()
                .status(HttpStatus.CONFLICT)
                .message(conflictException.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(responseError);
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler({ForbiddenException.class})
    protected ResponseEntity<ResponseError> ForbiddenException(ForbiddenException forbiddenException){
        final ResponseError responseError = ResponseError.builder()
                .status(HttpStatus.FORBIDDEN)
                .message(forbiddenException.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(responseError);
    }

    @ResponseStatus(HttpStatus.GONE)
    @ExceptionHandler({GoneException.class})
    protected ResponseEntity<ResponseError> GoneException(GoneException goneException){
        final ResponseError responseError = ResponseError.builder()
                .status(HttpStatus.GONE)
                .message(goneException.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.GONE).body(responseError);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({InternalServerException.class})
    protected ResponseEntity<ResponseError> InternalServerException(InternalServerException internalServerException){
        final ResponseError responseError = ResponseError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(internalServerException.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseError);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NotFoundException.class})
    protected ResponseEntity<ResponseError> NotFoundException(NotFoundException notFoundException){
        final ResponseError responseError = ResponseError.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message(notFoundException.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseError);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({UnauthorizedException.class})
    protected ResponseEntity<ResponseError> UnauthorizedException(UnauthorizedException unauthorizedException){
        final ResponseError responseError = ResponseError.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message(unauthorizedException.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseError);
    }
}
