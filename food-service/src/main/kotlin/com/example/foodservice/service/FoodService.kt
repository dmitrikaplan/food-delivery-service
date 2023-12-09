package com.example.foodservice.service

import com.example.foodservice.domain.entity.Food
import org.springframework.stereotype.Service

@Service
interface FoodService {
    fun getPage(pageNumber: Int): List<Food>
    fun getPageByCategoryName(pageNumber: Int, categoryName: String): List<Food>
    fun getPageByFoodName(pageNumber: Int, foodName: String): List<Food>
}