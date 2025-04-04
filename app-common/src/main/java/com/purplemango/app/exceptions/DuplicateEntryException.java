package com.purplemango.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateEntryException extends BaseException {
    public DuplicateEntryException(String message) {
        super(message);
    }

    public DuplicateEntryException(String message, String id) {
        super(message);
    }

    public DuplicateEntryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public DuplicateEntryException(String message, Throwable cause) {
        super(message, cause);
    }
}
