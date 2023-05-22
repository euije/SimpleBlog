package com.euije.Simple.common.advice;

import com.euije.Simple.common.dto.APIResponse;
import com.euije.Simple.common.dto.Error;
import com.euije.Simple.exception.model.MyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
public class ControllerExceptionAdvice {

    /**
     * 400 BAD_REQUEST
     */

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected APIResponse handleMethodArgumentNotValidException(final MethodArgumentNotValidException e) {
        FieldError fieldError = Objects.requireNonNull(e.getFieldError());
        return APIResponse.error(Error.REQUEST_VALIDATION_EXCEPTION, String.format("%s. (%s)", fieldError.getDefaultMessage(), fieldError.getField()));
    }

    /**
     * 500 Internal Server
     */
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @ExceptionHandler(Exception.class)
//    protected ApiResponse<Object> handleException(final Exception e) {
//        return ApiResponse.error(Error.INTERNAL_SERVER_ERROR);
//    }

    /**
     * custom error
     */
    @ExceptionHandler(MyException.class)
    protected ResponseEntity<APIResponse> handleSoptException(MyException e) {
        return ResponseEntity.status(e.getHttpStatus())
                .body(APIResponse.error(e.getError(), e.getMessage()));
    }
}
