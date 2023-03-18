package com.example.hospitalservice.service.impl;

import com.example.doctorservice.DoctorId;
import com.example.doctorservice.DoctorServiceGrpc;
import com.example.doctorservice.IdentityNumber;
import com.example.hospitalservice.client.DoctorServiceClient;
import com.example.hospitalservice.constants.ErrorMessages;
import com.example.hospitalservice.dto.request.AddDoctorRequest;
import com.example.hospitalservice.dto.response.DoctorDto;
import com.example.hospitalservice.dto.response.HospitalDto;
import com.example.hospitalservice.entity.Hospital;
import com.example.hospitalservice.exception.HospitalNotFoundException;
import com.example.hospitalservice.repository.HospitalRepository;
import com.example.hospitalservice.service.HospitalService;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HospitalServiceImpl implements HospitalService {

    private final HospitalRepository hospitalRepository;
    private final DoctorServiceClient doctorServiceClient;

    @GrpcClient("doctor-service")
    private DoctorServiceGrpc.DoctorServiceBlockingStub doctorServiceBlockingStub; // for sync communication
    // default stub can be used both sync and async communications.

    public HospitalServiceImpl(HospitalRepository hospitalRepository, DoctorServiceClient doctorServiceClient) {
        this.hospitalRepository = hospitalRepository;
        this.doctorServiceClient = doctorServiceClient;
    }
    @Override
    public void createHospital() {
        hospitalRepository.save(new Hospital());
    }

    @Override
    public void addDoctorToHospital(AddDoctorRequest request) {
      /*  final String doctorId = doctorServiceClient.getDoctorIdByIdentityNumber(request.getIdentityNumber())
                .getBody()
                .getDoctorId();*/

        final DoctorId doctorId = doctorServiceBlockingStub.getDoctorIdByIdentityNumber(IdentityNumber.newBuilder().setIdentityNumber(request.getIdentityNumber()).build());

        final Hospital hospital = hospitalRepository.findById(request.getId())
                .orElseThrow(() -> new HospitalNotFoundException(ErrorMessages.HOSPITAL_NOT_FOUND + request.getId()));

        //hospital.getDoctorList().add(doctorId);
        hospital.getDoctorList().add(doctorId.getDoctorId());
        hospitalRepository.save(hospital);
    }

    @Override
    public HospitalDto getHospitalById(String id) {

        final Hospital hospital =  hospitalRepository.findById(id)
                .orElseThrow(() -> new HospitalNotFoundException(ErrorMessages.HOSPITAL_NOT_FOUND + id));

        final List<DoctorDto> doctorDtoList = hospital.getDoctorList()
                .stream()
                .map(doctorId -> doctorServiceClient.getDoctorById(doctorId).getBody())
                .toList();
                return new HospitalDto(hospital.getId(), doctorDtoList);

    }

    @Override
    public List<String> getAllHospitalIds() {
        return hospitalRepository.findAll().stream().map(hospital -> hospital.getId())
                .collect(Collectors.toList());
    }
}
