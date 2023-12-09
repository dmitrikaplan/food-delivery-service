package com.example.foodservice.web.controller

import com.example.foodservice.service.FoodService
import com.example.foodservice.service.OrderService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/order/")
class OrderController(
    private val orderService: OrderService
) {

}