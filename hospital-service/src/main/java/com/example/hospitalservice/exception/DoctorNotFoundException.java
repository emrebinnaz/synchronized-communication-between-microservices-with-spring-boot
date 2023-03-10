package com.example.hospitalservice.exception;


public final class DoctorNotFoundException extends RuntimeException{

    private final ExceptionMessage exceptionMessage;

    public DoctorNotFoundException(ExceptionMessage exceptionMessage) {
        this.exceptionMessage = exceptionMessage;

    }
    public ExceptionMessage getExceptionMessage() {
        return this.exceptionMessage;
    }
}
