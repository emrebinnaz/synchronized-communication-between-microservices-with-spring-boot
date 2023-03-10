package com.example.hospitalservice.dto.response

data class BaseResponse @JvmOverloads constructor(
        val message: String,
        val isSuccess: Boolean,
){
}