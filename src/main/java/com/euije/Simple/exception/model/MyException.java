package com.euije.Simple.exception.model;

import lombok.Getter;
import com.euije.Simple.common.dto.Error;

@Getter
public class MyException extends RuntimeException {

    private final Error error;

    public MyException(Error error, String message) {
        super(message);
        this.error = error;
    }

    public int getHttpStatus() {
        return error.getHttpStatusCode();
    }
}
