package com.example.foodservice.web.controller

import com.example.foodservice.service.FoodService
import com.example.foodservice.web.converter.FoodConverter
import com.example.foodservice.web.dto.FoodDto
import jakarta.validation.constraints.Min
import org.slf4j.LoggerFactory
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/food/")
@CrossOrigin(origins = ["http://localhost:8080/", "https://kaplaan.ru/"])
class FoodController(
    private val foodService: FoodService
) {

    private val log = LoggerFactory.getLogger(javaClass)

    @GetMapping("/page/{pageNumber}")
    fun getPage(
        @Validated @Min(0)
        @PathVariable pageNumber: Int)
    : List<FoodDto> {
        val food = foodService.getPage(pageNumber)
        return FoodConverter.toDto(food)
    }

    @GetMapping("/page/{pageNumber}/{categoryName}")
    fun getPage(
        @Validated @Min(0)
        @PathVariable pageNumber: Int,
        @PathVariable categoryName: String
    ): List<FoodDto>{
        val food = foodService.getPageByCategoryName(pageNumber, categoryName)
        return FoodConverter.toDto(food)
    }

    @GetMapping("/page/{pageNumber}/{foodName}")
    fun getPageByFoodName(
        @Validated @Min(0)
        @PathVariable pageNumber: Int,
        @PathVariable foodName: String
    ): List<FoodDto> {
        val food = foodService.getPageByFoodName(pageNumber, foodName)
        return FoodConverter.toDto(food)
    }

    //@PreAuthorize("hasRole(admin)")
    @PostMapping("/add")
    fun addFood(@RequestBody foodDto: FoodDto){
        foodService.addFood(FoodConverter.toEntity(foodDto))
    }
}