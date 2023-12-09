package com.example.foodservice.web.converter

import com.example.foodservice.domain.entity.Food
import com.example.foodservice.web.dto.FoodDto

object FoodConverter: Converter<FoodDto, Food> {
    override fun toDto(entity: Food)
    = FoodDto(
        entity.foodId!!,
        entity.foodName,
        entity.description,
        entity.price!!,
        entity.imageUrl,
        entity.categories.map(CategoryConverter::toDto))

    override fun toEntity(dto: FoodDto) = Food().apply {
        // TODO
    }
}