package com.bside.study.common.api;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiError {
    private final String message;
    private final int status;

    ApiError(Throwable throwable, HttpStatus status) {
        this(throwable.getCause().getMessage(), status);
    }

    ApiError(String message, HttpStatus status) {
        this.message = message;
        this.status = status.value();
    }
}
