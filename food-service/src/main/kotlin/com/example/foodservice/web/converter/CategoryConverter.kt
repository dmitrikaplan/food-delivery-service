package com.example.foodservice.web.converter

import com.example.foodservice.domain.entity.Category
import com.example.foodservice.web.dto.CategoryDto

object CategoryConverter: Converter<CategoryDto, Category> {
    override fun toDto(entity: Category) : CategoryDto =
        CategoryDto(
            categoryId = entity.categoryId!!,
            categoryName = entity.categoryName,
        )

    override fun toEntity(dto: CategoryDto) =
         Category().apply {
             this.categoryId = dto.categoryId
             this.categoryName = dto.categoryName
         }
}