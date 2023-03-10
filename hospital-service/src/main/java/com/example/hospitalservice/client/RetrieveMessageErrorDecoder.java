package com.example.hospitalservice.client;

import com.example.hospitalservice.exception.DoctorNotFoundException;
import com.example.hospitalservice.exception.ExceptionMessage;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class RetrieveMessageErrorDecoder implements ErrorDecoder {
    // It catches the error where comes from Feign Clients.

    private final ErrorDecoder errorDecoder = new Default();
    // When there is a feign client error, "decode" function runs.
    @Override
    public Exception decode(String methodKey, Response response) {
        ExceptionMessage message = null;
        try (InputStream body = response.body().asInputStream()){
            message = new ExceptionMessage((String) response.headers().get("date").toArray()[0],
                    response.status(),
                    HttpStatus.resolve(response.status()).getReasonPhrase(),
                    IOUtils.toString(body, StandardCharsets.UTF_8),
                    response.request().url());

        } catch (IOException exception) {
            return new Exception(exception.getMessage());
        }
        switch (response.status()) {
            case 404:
                throw new DoctorNotFoundException(message);
            default:
                return errorDecoder.decode(methodKey, response);
        }
    }
}
