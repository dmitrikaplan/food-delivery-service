package com.example.foodservice.model.dto

data class Food(
    val foodId: Int,
    val foodName: String,
    val description: String,
    val price: Int,
    val imageUrl: String,
    val categories: List<Category>
)
