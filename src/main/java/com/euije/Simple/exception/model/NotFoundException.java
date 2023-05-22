package com.euije.Simple.exception.model;

import com.euije.Simple.common.dto.Error;

public class NotFoundException extends MyException {
    public NotFoundException(Error error, String message) {
        super(error, message);
    }
}