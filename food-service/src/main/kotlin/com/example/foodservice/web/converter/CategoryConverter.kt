package com.example.foodservice.web.converter

import com.example.foodservice.domain.entity.Category
import com.example.foodservice.web.dto.CategoryDto

object CategoryConverter: Converter<CategoryDto, Category> {
    override fun toDto(entity: Category): CategoryDto {
        TODO("Not yet implemented")
    }

    override fun toEntity(dto: CategoryDto): Category {
        TODO("Not yet implemented")
    }
}