package com.example.doctorservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class DoctorNotFoundException extends AbstractBaseException{

    public DoctorNotFoundException(String s) {
        super(s);
    }
}
