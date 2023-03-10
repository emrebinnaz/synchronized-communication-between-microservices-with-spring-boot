package com.example.hospitalservice.dto.response

import java.time.LocalDate

data class DoctorDto @JvmOverloads constructor(
        val id: String?,
        val name: String?,
        val surname: String?,
        val age: Int?,
        val identityNumber: String?,
        val birthDate: LocalDate?
){
}