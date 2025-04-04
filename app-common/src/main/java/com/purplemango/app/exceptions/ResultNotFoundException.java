package com.purplemango.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResultNotFoundException extends BaseException{

    public ResultNotFoundException() {
        super();
    }
    public ResultNotFoundException(String message) {
        super(message);
    }

    public ResultNotFoundException(String message, String id) {
        super(message);
    }

    public ResultNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ResultNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
