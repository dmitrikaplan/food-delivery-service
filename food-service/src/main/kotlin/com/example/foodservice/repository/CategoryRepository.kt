package com.example.foodservice.repository

import com.example.foodservice.model.entity.CategoryEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CategoryRepository: JpaRepository<CategoryEntity, Int> {

}