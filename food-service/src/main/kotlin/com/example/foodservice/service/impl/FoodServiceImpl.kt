package com.example.foodservice.service.impl

import com.example.foodservice.domain.entity.Food
import com.example.foodservice.repository.FoodRepository
import com.example.foodservice.service.FoodService
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class FoodServiceImpl(
    private val foodRepository: FoodRepository
): FoodService {
    @Value("\${page.size}")
    private var pageSize: Int? = null
    override fun getPage(pageNumber: Int) = foodRepository.findAll(PageRequest.of(pageNumber, pageSize!!)).content

    override fun getPageByCategoryName(pageNumber: Int, categoryName: String) = foodRepository.findAll().filter {
        it.categories.map {
                category -> category.categoryName
        }.contains(categoryName)
    }.take(pageSize!!)

    override fun getPageByFoodName(pageNumber: Int, foodName: String) = foodRepository.findAllByFoodNameContaining(
        PageRequest.of(pageNumber, pageSize!!),
        foodName
    )
}