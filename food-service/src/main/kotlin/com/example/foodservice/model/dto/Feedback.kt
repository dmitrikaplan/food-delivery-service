package com.example.foodservice.model.dto

data class Feedback(
    val userId: Int,
    val foodId: Int,
    val rating: Int,
    val comment: String
)
