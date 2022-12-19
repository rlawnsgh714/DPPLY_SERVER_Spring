package com.stuent.dpply.common.exception;

import com.stuent.dpply.common.exception.error.ErrorProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class CustomException extends RuntimeException {
    private ErrorProperty errorProperty;
}
