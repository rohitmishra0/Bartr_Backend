package com.bartr.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class Handler {

    @ExceptionHandler(DatabaseAccessException.class)
    public ResponseEntity<String> handleDatabaseAccessException(DatabaseAccessException ex) {
        return new ResponseEntity<>("Database Error: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNameNotFoundException.class)
    public ResponseEntity<String> handleUserNameNotFoundException(UserNameNotFoundException ex) {
        return new ResponseEntity<>("Bad Credentials: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handeleGenericException(Exception ex){
        return new ResponseEntity<>("Error Occur: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(UserIsNotAnAdmin.class)
    public ResponseEntity<String> handleUserIsNotAnAdmin(UserIsNotAnAdmin ex) {
        return new ResponseEntity<>("Bad Credentials: " + ex.getMessage(), HttpStatus.BAD_REQUEST);

}

}
