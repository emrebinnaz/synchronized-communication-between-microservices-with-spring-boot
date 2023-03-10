package com.example.doctorservice.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.GenericGenerator
import java.time.LocalDate

@Entity
@Table(name = "doctors")
data class Doctor @JvmOverloads constructor(
        @Id
        @GeneratedValue(generator = "UUID")
        @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
        val id: String? = "",
        val name: String? = "",
        val surname: String? = "",
        val age: Int = 0,
        val identityNumber: String? = "",
        val birthDate : LocalDate? = null
){


}