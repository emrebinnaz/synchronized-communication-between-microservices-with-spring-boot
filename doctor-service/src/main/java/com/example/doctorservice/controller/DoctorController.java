package com.example.doctorservice.controller;

import com.example.doctorservice.dto.response.DoctorDto;
import com.example.doctorservice.dto.response.DoctorIdDto;
import com.example.doctorservice.service.DoctorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/doctors")
@Validated
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(final DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping
    public ResponseEntity<List<DoctorDto>> getAllDoctors() {
        return ResponseEntity.ok(doctorService.getAllDoctors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorDto> getDoctorById(@PathVariable final String id) {
        return ResponseEntity.ok(doctorService.findById(id));
    }

    @GetMapping("/number/{identityNumber}")
    public ResponseEntity<DoctorIdDto> getDoctorIdByIdentityNumber(@PathVariable final String identityNumber){
        return ResponseEntity.ok(doctorService.getDoctorIdByIdentityNumber(identityNumber));
    }

    @PostMapping
    public ResponseEntity<Void> addDoctor() {
        doctorService.addDoctor();
        return ResponseEntity.status(HttpStatus.CREATED.value()).build();
    }
}
