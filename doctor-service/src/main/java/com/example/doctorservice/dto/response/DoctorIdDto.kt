package com.example.doctorservice.dto.response;

import com.example.doctorservice.dto.response.projection.DoctorIdProjectionItem

data class DoctorIdDto @JvmOverloads constructor(
        val doctorId: String,
) {
    companion object {
        @JvmStatic
        fun convert(from: DoctorIdProjectionItem) : DoctorIdDto {
            return DoctorIdDto(from.id())
        }
    }
}
