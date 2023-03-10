package com.example.hospitalservice.client;

import com.example.hospitalservice.dto.response.DoctorDto;
import com.example.hospitalservice.dto.response.DoctorIdDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;

@FeignClient(name = "doctor-service", path = "/v1/doctors")
public interface DoctorServiceClient {
    //TODO: Circuit breaker implementation

    Logger logger = LoggerFactory.getLogger(DoctorServiceClient.class);

    @GetMapping("/number/{identityNumber}")
    @CircuitBreaker(name = "getDoctorIdByIdentityNumber_CircuitBreaker", fallbackMethod = "getDoctorIdByIdentityNumberFallback")
    ResponseEntity<DoctorIdDto> getDoctorIdByIdentityNumber(@PathVariable(value = "identityNumber") String identityNumber);

    default ResponseEntity<DoctorIdDto> getDoctorIdByIdentityNumberFallback(String identityNumber, Exception exception) {
        //Exception parameter comes from Feign Client.
        logger.info("Doctor is not found by identity number");
        return ResponseEntity.ok(new DoctorIdDto("default id"));
    }

    @GetMapping("/{id}")
    //@CircuitBreaker(name = "getDoctorById_CircuitBreaker", fallbackMethod = "getDoctorByIdFallback")
    ResponseEntity<DoctorDto> getDoctorById(@PathVariable(value = "id") String id);

    /*default ResponseEntity<DoctorDto> getDoctorByIdFallback(String id, Exception exception) {
        return ResponseEntity.ok(new DoctorDto("default id", "default name", "default surname",
                                               12, "default identity number", LocalDate.now()));
    }*/
}
