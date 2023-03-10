package com.example.doctorservice.service;

import com.example.doctorservice.dto.response.DoctorDto;
import com.example.doctorservice.dto.response.DoctorIdDto;

import java.util.List;

public interface DoctorService {

    List<DoctorDto> getAllDoctors();

    DoctorDto findById(String id);

    DoctorIdDto getDoctorIdByIdentityNumber(String identityNumber);

    void addDoctor();
}
