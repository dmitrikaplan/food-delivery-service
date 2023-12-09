package com.example.foodservice.domain.exception.food

class FoodNotFoundException: RuntimeException() {

    override val message: String
        get() = "Блюдо не найдено!"
}