package com.bartr.exception;

public class UserNameNotFoundException extends RuntimeException {

    public UserNameNotFoundException(String message) {
        super(message);
    }

    public UserNameNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
