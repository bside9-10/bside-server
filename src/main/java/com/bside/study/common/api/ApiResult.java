package com.bside.study.common.api;

import lombok.Getter;

@Getter
public class ApiResult<T> {
    private final boolean success;
    private final T response;
    private final ApiError error;

    ApiResult(boolean success, T response, ApiError error) {
        this.success = success;
        this.response = response;
        this.error = error;
    }
}
