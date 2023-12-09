package com.example.foodservice.web.dto

data class FeedbackDto(
    val userId: Int,
    val foodId: Int,
    val rating: Int,
    val comment: String
)
