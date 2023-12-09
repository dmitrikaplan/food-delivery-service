package com.example.foodservice.web.dto

data class FeedbackDto(
    val username: String,
    val foodId: Int,
    val rating: Int,
    val comment: String
)
