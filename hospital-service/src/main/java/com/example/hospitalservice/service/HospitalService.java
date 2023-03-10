package com.example.hospitalservice.service;

import com.example.hospitalservice.dto.request.AddDoctorRequest;
import com.example.hospitalservice.dto.response.HospitalDto;

import java.util.List;

public interface HospitalService {
    void createHospital();
    void addDoctorToHospital(AddDoctorRequest request);
    HospitalDto getHospitalById(String id);
    List<String> getAllHospitalIds();

}
