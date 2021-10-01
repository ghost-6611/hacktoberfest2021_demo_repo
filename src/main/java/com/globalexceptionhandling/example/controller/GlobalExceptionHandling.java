package com.globalexceptionhandling.example.controller;

import com.globalexceptionhandling.example.response.APIResponse;
import com.globalexceptionhandling.example.exception.MandatoryDataException;
import com.globalexceptionhandling.example.response.ExceptionResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandling extends ResponseEntityExceptionHandler {

    ExceptionResponse exceptionResponse = null;

    @ExceptionHandler(MandatoryDataException.class)
    public ResponseEntity<Object> handleMandatoryDataException(MandatoryDataException exception) {
        exceptionResponse = ExceptionResponse.builder().message("Mandatory data missing")
                .ExceptionName(exception.getClass().getName()).build();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException exception) {
        exceptionResponse = ExceptionResponse.builder().message("No student found with this id")
                .ExceptionName(exception.getClass().getName()).build();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        exceptionResponse = ExceptionResponse.builder().message("Wrong Http method provided").ExceptionName(ex.getClass().getName()).build();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(RuntimeException exception) {
         exceptionResponse = ExceptionResponse.builder()
                .message("Operation failed").ExceptionName(exception.getClass().getName()).build();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.EXPECTATION_FAILED);
    }

}



