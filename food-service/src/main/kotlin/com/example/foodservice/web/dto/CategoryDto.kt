package com.example.foodservice.web.dto

data class CategoryDto(
    val categoryId: Int,
    val categoryName: String,
    val food: List<FoodDto>
)
