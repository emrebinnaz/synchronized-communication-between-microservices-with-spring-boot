package com.example.hospitalservice.entity

import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator
import java.util.*

@Entity
@Table(name = "hospitals")
data class Hospital @JvmOverloads constructor(
        @Id
        @Column(name = "id")
        @GeneratedValue(generator = "UUID")
        @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
        val id: String? = "",

        @ElementCollection
        val doctorList: List<String> = ArrayList()
) {
}