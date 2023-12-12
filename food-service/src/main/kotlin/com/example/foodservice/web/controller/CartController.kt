package com.example.foodservice.web.controller

import com.example.domain.exception.UserNotFoundException
import com.example.foodservice.service.CartService
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
@RequestMapping("/api/v1/cart")
@CrossOrigin(origins = ["http://localhost:8080"])
class CartController(
    private val cartService: CartService
) {

    private val log = LoggerFactory.getLogger(javaClass)

    @PostMapping()
    fun createCart(principal: Principal): ResponseEntity<Int> {
        return try {
            log.info(principal.name)
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