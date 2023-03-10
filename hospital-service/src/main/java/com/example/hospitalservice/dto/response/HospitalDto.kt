package com.example.hospitalservice.dto.response

data class HospitalDto @JvmOverloads constructor(
        val id: String,
        val doctorList: List<DoctorDto>? = ArrayList()
){
}