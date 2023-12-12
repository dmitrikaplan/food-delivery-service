package com.example.foodservice.web.converter

import com.example.foodservice.domain.entity.Food
import com.example.foodservice.web.dto.FoodDto

object FoodConverter: Converter<FoodDto, Food> {
    override fun toDto(entity: Food)
    = FoodDto(
        entity.foodId,
        entity.foodName,
        entity.description,
        entity.price!!,
        entity.base64Image,
        entity.categories.map(CategoryConverter::toDto)
    )

    override fun toEntity(dto: FoodDto) = Food().apply {
        this.foodId = dto.foodId
        this.foodName = dto.foodName
        this.description = dto.description
        this.price = dto.price
        this.base64Image = dto.base64Image
        this.categories = dto.categories.map(CategoryConverter::toEntity)
    }

    fun toDto(entities: List<Food>) = entities.map { toDto(it) }
}