package com.example.foodservice.model.converter

import com.example.foodservice.model.dto.Category
import com.example.foodservice.model.entity.CategoryEntity

object CategoryConverter: Converter<Category, CategoryEntity> {
    override fun toDto(entity: CategoryEntity): Category {
        TODO("Not yet implemented")
    }

    override fun toEntity(dto: Category): CategoryEntity {
        TODO("Not yet implemented")
    }
}