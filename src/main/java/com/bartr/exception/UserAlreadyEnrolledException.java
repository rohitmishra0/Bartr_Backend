package com.bartr.exception;

public class UserAlreadyEnrolledException extends RuntimeException {
    public UserAlreadyEnrolledException(String message) {
        super(message);
    }

    public UserAlreadyEnrolledException(String message, Throwable cause) {
        super(message, cause);
    }

}
