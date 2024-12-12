package com.luv2Code.demo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StudentRestExceptionHandler {

    // Adding the exception handling code here

    /**
     * Exception handler for StudentNotFoundException.
     *
     * The @ExceptionHandler annotation maps this method to handle exceptions
     * of type StudentNotFoundException.
     *
     * @param exception The exception object.
     * @return A ResponseEntity containing the error response and HTTP status.
     */
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exception) {
        // Create a StudentErrorResponse object with error details
        StudentErrorResponse errorResponse = new StudentErrorResponse();
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setTimeStamp(System.currentTimeMillis());

        // Return the error response as a ResponseEntity
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Generic exception handler for any other exceptions.
     *
     * The @ExceptionHandler annotation maps this method to handle all other exceptions.
     *
     * @param exception The exception object.
     * @return A ResponseEntity containing the error response and HTTP status.
     */
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(Exception exception) {
        // Create a StudentErrorResponse object with error details
        StudentErrorResponse errorResponse = new StudentErrorResponse();
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setTimeStamp(System.currentTimeMillis());

        // Return the error response as a ResponseEntity
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
