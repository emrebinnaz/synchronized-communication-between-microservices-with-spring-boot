package com.example.doctorservice.repository;

import com.example.doctorservice.dto.response.projection.DoctorIdProjectionItem;
import com.example.doctorservice.entity.Doctor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, String> {
    Optional<DoctorIdProjectionItem> getDoctorIdByIdentityNumber(String identityNumber);
}
