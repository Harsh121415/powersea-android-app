package com.example.powerseva.model

data class Complaint(
    val id: Int,
    val area: String,
    val ward: String,
    val problemType: String,
    val description: String,
    val status: String,
    val supportCount: Int,
    val createdAt: String
)