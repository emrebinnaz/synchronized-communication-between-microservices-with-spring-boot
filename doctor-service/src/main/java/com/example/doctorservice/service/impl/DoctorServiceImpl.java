package com.example.doctorservice.service.impl;

import com.example.doctorservice.constants.ErrorMessages;
import com.example.doctorservice.dto.response.DoctorDto;
import com.example.doctorservice.dto.response.DoctorIdDto;
import com.example.doctorservice.entity.Doctor;
import com.example.doctorservice.exception.DoctorNotFoundException;
import com.example.doctorservice.repository.DoctorRepository;
import com.example.doctorservice.service.DoctorService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public List<DoctorDto> getAllDoctors() {
        return doctorRepository.findAll()
                .stream().map(DoctorDto::convert)
                .collect(Collectors.toList());
    }

    @Override
    public DoctorDto findById(String id) {
       return doctorRepository.findById(id)
               .map(DoctorDto::convert)
               .orElseThrow(() -> new DoctorNotFoundException(ErrorMessages.DOCTOR_NOT_FOUND_BY + id));
    }

    @Override
    public DoctorIdDto getDoctorIdByIdentityNumber(String identityNumber) {
        return doctorRepository.getDoctorIdByIdentityNumber(identityNumber)
                .map(DoctorIdDto::convert)
                .orElseThrow(() -> new DoctorNotFoundException(ErrorMessages.DOCTOR_NOT_FOUND_BY + identityNumber));
    }

    @Override
    public void addDoctor() {
        Doctor doctor = new Doctor(UUID.randomUUID().toString(), "Emre", "Binnaz", 23, "1111111111", LocalDate.now());

        doctorRepository.save(doctor);

    }
}
