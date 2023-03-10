package com.example.doctorservice.dto.response;

import com.example.doctorservice.entity.Doctor
import java.time.LocalDate

data class DoctorDto @JvmOverloads constructor(
        val id: String?,
        val name: String?,
        val surname: String?,
        val age: Int?,
        val identityNumber: String?,
        val birthDate: LocalDate?
){
    companion object {
        @JvmStatic
        fun convert(from: Doctor): DoctorDto {
            return DoctorDto(
                    from.id,
                    from.name,
                    from.surname,
                    from.age,
                    from.identityNumber,
                    from.birthDate
            )
        }
    }
}
