package com.purplemango.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ReservationDuplicateException extends BaseException {
    public ReservationDuplicateException(String message) {
        super(message);
    }

    public ReservationDuplicateException(String message, String id) {
        super(message);
    }

    public ReservationDuplicateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ReservationDuplicateException(String message, Throwable cause) {
        super(message, cause);
    }
}
