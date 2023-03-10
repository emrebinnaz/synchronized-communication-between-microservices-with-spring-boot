package com.example.hospitalservice.dto.response

data class DoctorIdDto @JvmOverloads constructor(

        val doctorId: String? = "",
) {
    companion object {
        @JvmStatic
        fun convert(doctorId: String) : DoctorIdDto{
            return DoctorIdDto(doctorId)
        }
    }

}
