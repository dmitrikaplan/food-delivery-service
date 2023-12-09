package com.example.foodservice.web.dto

data class FoodDto(
    val foodId: Int,
    val foodName: String,
    val description: String,
    val price: Int,
    val imageUrl: String,
    val categories: List<CategoryDto>
)
