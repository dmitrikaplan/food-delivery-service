package com.example.foodservice.web.controller

import com.example.domain.exception.UserNotFoundException
import com.example.foodservice.domain.exception.cart.CartNotFoundException
import com.example.foodservice.domain.exception.cartDetail.CartDetailNotFoundException
import com.example.foodservice.domain.exception.food.FoodNotFoundException
import com.example.foodservice.service.CartDetailService
import com.example.foodservice.service.CartService
import com.example.foodservice.web.dto.CartDetailDto
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/cart")
@CrossOrigin(origins = ["http://localhost:8080/"])
class CartController(
    private val cartService: CartService
) {

    private val log = LoggerFactory.getLogger(javaClass)

    @PostMapping("/{userId}")
    fun createCart(@PathVariable userId: Int): ResponseEntity<Int> {
        return try {
            ResponseEntity.ok().body(cartService.create(userId))
        } catch (e: UserNotFoundException) {
            log.error(e.message)
            ResponseEntity.badRequest().build()
        }
    }


    @DeleteMapping("/{cartId}")
    fun deleteCart(@PathVariable cartId: Int){
        cartService.delete(cartId)
    }
}