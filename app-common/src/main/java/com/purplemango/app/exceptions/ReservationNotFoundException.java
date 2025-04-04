package com.purplemango.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ReservationNotFoundException extends BaseException{

    public ReservationNotFoundException(String message) {
        super(message);
    }

    public ReservationNotFoundException(String message, String id) {
        super(message);
    }

    public ReservationNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ReservationNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
