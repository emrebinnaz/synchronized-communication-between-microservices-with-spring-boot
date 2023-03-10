package com.example.hospitalservice.controller;

import com.example.hospitalservice.dto.request.AddDoctorRequest;
import com.example.hospitalservice.dto.response.HospitalDto;
import com.example.hospitalservice.service.HospitalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/v1/hospitals")
public class HospitalController {

    private final HospitalService hospitalService;

    public HospitalController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    @PostMapping
    public ResponseEntity<Void> createHospital() {
        hospitalService.createHospital();
        return ResponseEntity.status(HttpStatus.CREATED.value()).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<HospitalDto> getHospitalById(@PathVariable String id) {
        return ResponseEntity.ok(hospitalService.getHospitalById(id));
    }

    @PutMapping
    public ResponseEntity<Void> addDoctorToHospital(@RequestBody final AddDoctorRequest addDoctorRequest) {
        hospitalService.addDoctorToHospital(addDoctorRequest);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<String>> getAllHospitalIds() {
        return ResponseEntity.ok(hospitalService.getAllHospitalIds());
    }
}
