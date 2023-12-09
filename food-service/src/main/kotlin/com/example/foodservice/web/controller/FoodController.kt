package com.example.foodservice.web.controller

import com.example.foodservice.service.FoodService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/food/")
class FoodController(
    private val foodService: FoodService
) {
    // TODO implement get post patch delete
}