package com.example.foodservice.web.converter

import com.example.foodservice.domain.entity.Category
import com.example.foodservice.web.dto.CategoryDto

object CategoryConverter: Converter<CategoryDto, Category> {
    override fun toDto(entity: Category) : CategoryDto = CategoryDto(
        categoryId = entity.categoryId!!,
        categoryName = entity.categoryName,
        food = entity.food.map(FoodConverter::toDto),
    )

    override fun toEntity(dto: CategoryDto) = throw Error()
}