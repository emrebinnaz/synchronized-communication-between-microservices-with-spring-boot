package com.example.doctorservice.service.impl;


import com.example.doctorservice.DoctorId;
import com.example.doctorservice.DoctorServiceGrpc;
import com.example.doctorservice.IdentityNumber;
import com.example.doctorservice.constants.ErrorMessages;
import com.example.doctorservice.dto.response.DoctorIdDto;
import com.example.doctorservice.dto.response.projection.DoctorIdProjectionItem;
import com.example.doctorservice.exception.DoctorNotFoundException;
import com.example.doctorservice.repository.DoctorRepository;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@GrpcService
public class DoctorGrpcServiceImpl extends DoctorServiceGrpc.DoctorServiceImplBase {

    private static final Logger logger = LoggerFactory.getLogger(DoctorGrpcServiceImpl.class);
    private final DoctorRepository doctorRepository;

    public DoctorGrpcServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public void getDoctorIdByIdentityNumber(IdentityNumber request, StreamObserver<DoctorId> responseObserver) {
        logger.info("Grpc call received: " + request.getIdentityNumber());
        final DoctorIdDto doctorIdDto = doctorRepository.getDoctorIdByIdentityNumber(request.getIdentityNumber())
                .map(DoctorIdDto::convert)
                .orElseThrow(() -> new DoctorNotFoundException(ErrorMessages.DOCTOR_NOT_FOUND_BY + request.getIdentityNumber()));
        responseObserver.onNext(
                DoctorId.newBuilder()
                        .setDoctorId(doctorIdDto.getDoctorId())
                        .build()
        );
        responseObserver.onCompleted();
    }
}
