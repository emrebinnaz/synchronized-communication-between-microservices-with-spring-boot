package com.example.doctorservice.utils;

object StringUtils {

    fun maskIdentityNumber(identityNumber: String) : String {
        return identityNumber.replaceRange(2, 8, "*");
    }
}
