package com.example.foodservice.web.controller

import com.example.foodservice.domain.exception.cart.CartNotFoundException
import com.example.foodservice.domain.exception.cartDetail.CartDetailNotFoundException
import com.example.foodservice.domain.exception.food.FoodNotFoundException
import com.example.foodservice.service.CartDetailService
import com.example.foodservice.web.dto.CartDetailDto
import com.example.foodservice.web.validation.OnUpdate
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/cart-detail")
@CrossOrigin(origins = ["http://localhost:8080/", "https://kaplaan.ru/"])
class CartDetailController(
    private val cartDetailService: CartDetailService
) {

    private val log = LoggerFactory.getLogger(javaClass)

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
            log.error(e.message)
            ResponseEntity.badRequest().body(e.message)
        } catch (e: FoodNotFoundException) {
            log.error(e.message)
            ResponseEntity.badRequest().body(e.message)
        }
    }

    @PutMapping
    fun update(
        @RequestBody @Validated(OnUpdate::class)
        cartDetailDto: CartDetailDto
    ): ResponseEntity<Any> {
        return try{
            cartDetailService.update(
                cartDetailId = cartDetailDto.cartDetailId!!,
                quantity = cartDetailDto.quantity
            )
            ResponseEntity.ok().build()
        } catch (e: CartDetailNotFoundException){
            log.error(e.message)
            ResponseEntity.badRequest().body(e.message)
        }


    }

    @DeleteMapping("/{cartDetailId}")
    fun delete(@PathVariable cartDetailId: Int) {
        cartDetailService.delete(cartDetailId)
    }
}