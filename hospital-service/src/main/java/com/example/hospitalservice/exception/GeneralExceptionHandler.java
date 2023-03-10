package com.example.hospitalservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GeneralExceptionHandler {

    @ExceptionHandler(HospitalNotFoundException.class)
    public ResponseEntity<?> handle(HospitalNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DoctorNotFoundException.class)
    public ResponseEntity<ExceptionMessage> handle(DoctorNotFoundException exception) {
        return new ResponseEntity<>(exception.getExceptionMessage(),
                                    HttpStatus.resolve(exception.getExceptionMessage().status()));
    }
}
