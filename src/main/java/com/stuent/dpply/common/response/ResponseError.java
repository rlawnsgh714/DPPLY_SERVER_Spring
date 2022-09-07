package com.stuent.dpply.common.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ResponseError extends Response {

    @Builder
    public ResponseError(HttpStatus status, String message) {
        super(status, message);
    }
}
