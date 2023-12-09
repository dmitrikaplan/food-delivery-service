package com.example.foodservice.model.converter

import com.example.foodservice.model.dto.Food
import com.example.foodservice.model.entity.FoodEntity

object FoodConverter: Converter<Food, FoodEntity> {
    override fun toDto(entity: FoodEntity) = Food(entity.foodId!!, entity.foodName, entity.description, entity.price!!, entity.imageUrl, entity.categories.map(CategoryConverter::toDto))

    override fun toEntity(dto: Food) = FoodEntity().apply {
        // TODO
    }
}