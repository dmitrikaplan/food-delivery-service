package com.example.foodservice.web.controller

import com.example.domain.exception.UserNotFoundException
import com.example.foodservice.service.CartService
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController
@RequestMapping("/api/v1/cart")
@CrossOrigin(origins = ["http://localhost:8080/", "https://kaplaan.ru/"])
class CartController(
    private val cartService: CartService
) {

    private val log = LoggerFactory.getLogger(javaClass)

    @PostMapping
    fun createCart(principal: Principal): ResponseEntity<Int> {
        return try {
            ResponseEntity.ok().body(cartService.create(principal.name))
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