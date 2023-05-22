package com.euije.Simple.common.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class APIResponse<T> {

    private final int code;
    private final String message;
    private T data;

    public static APIResponse success(SuccessStatus successStatus) {
        return new APIResponse<>(successStatus.getHttpStatus().value(), successStatus.getMessage());
    }

    public static <T> APIResponse<T> success(SuccessStatus successStatus, T data) {
        return new APIResponse<T>(successStatus.getHttpStatus().value(), successStatus.getMessage(), data);
    }

    public static APIResponse error(Error error) {
        return new APIResponse<>(error.getHttpStatus().value(), error.getMessage());
    }

    public static APIResponse error(Error error, String message) {
        return new APIResponse<>(error.getHttpStatusCode(), message);
    }
}