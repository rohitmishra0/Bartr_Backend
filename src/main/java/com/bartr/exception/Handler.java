package com.bartr.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class Handler {

    // Utility method to create a structured JSON-like response
    private Map<String, Object> createResponse(String message, HttpStatus status) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("error", true);
        errorResponse.put("message", message);
        errorResponse.put("status", status.value());
        errorResponse.put("statusText", status.getReasonPhrase());
        return errorResponse;
    }

    @ExceptionHandler(DatabaseAccessException.class)
    public ResponseEntity<Map<String, Object>> handleDatabaseAccessException(DatabaseAccessException ex) {
        Map<String, Object> response = createResponse("Database Error: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNameNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleUserNameNotFoundException(UserNameNotFoundException ex) {
        Map<String, Object> response = createResponse("Bad Credentials: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericException(Exception ex) {
        Map<String, Object> response = createResponse("Error Occurred: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserIsNotAnAdmin.class)
    public ResponseEntity<Map<String, Object>> handleUserIsNotAnAdmin(UserIsNotAnAdmin ex) {
        Map<String, Object> response = createResponse("Forbidden: " + ex.getMessage(), HttpStatus.FORBIDDEN);
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }
}
