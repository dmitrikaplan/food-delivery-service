package com.example.foodservice.web.controller

import com.example.domain.exception.UserNotFoundException
import com.example.foodservice.domain.exception.cart.CartNotFoundException
import com.example.foodservice.domain.exception.cartDetail.CartDetailNotFoundException
import com.example.foodservice.domain.exception.food.FoodNotFoundException
import com.example.foodservice.service.CartDetailService
import com.example.foodservice.service.CartService
import com.example.foodservice.web.dto.CartDetailDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/cart")
class CartController(
    private val cartDetailService: CartDetailService,
    private val cartService: CartService
) {


    @PostMapping
    fun addToCart(@RequestBody cartDetailDto: CartDetailDto): ResponseEntity<String> {
        return try {
            val id = cartDetailService.save(
                cartId = cartDetailDto.cartId,
                foodId = cartDetailDto.foodId,
                quantity = cartDetailDto.quantity
            )
            ResponseEntity.ok().body(id.toString())
        } catch (e: CartNotFoundException) {
            ResponseEntity.badRequest().body(e.message)
        } catch (e: FoodNotFoundException) {
            ResponseEntity.badRequest().body(e.message)
        }
    }

    @PostMapping("/create/{userId}")
    fun createCart(@PathVariable userId: Int): ResponseEntity<Int> {
        return try {
            ResponseEntity.ok().body(cartService.create(userId))
        } catch (e: UserNotFoundException) {
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping()
    fun update(@RequestBody cartDetailDto: CartDetailDto): ResponseEntity<Any> {
        return try{
            cartDetailService.update(
                cartDetailId = cartDetailDto.cartDetailId,
                quantity = cartDetailDto.quantity
            )
            ResponseEntity.ok().build()
        } catch (e: CartDetailNotFoundException){
            ResponseEntity.badRequest().body(e.message)
        }


    }

    @DeleteMapping("/{cartDetailId}")
    fun delete(@PathVariable cartDetailId: Int) {
        cartDetailService.delete(cartDetailId)
    }

}