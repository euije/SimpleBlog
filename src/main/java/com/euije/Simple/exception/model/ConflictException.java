package com.euije.Simple.exception.model;

import com.euije.Simple.common.dto.Error;

public class ConflictException extends MyException {
    public ConflictException(Error error, String message) {
        super(error, message);
    }
}