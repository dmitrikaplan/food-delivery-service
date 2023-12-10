package com.example.foodservice.web.controller

import com.example.domain.exception.UserNotFoundException
import com.example.foodservice.domain.entity.Address
import com.example.foodservice.domain.entity.Order
import com.example.foodservice.domain.exception.address.AddressNotFoundException
import com.example.foodservice.domain.exception.cart.CartNotFoundException
import com.example.foodservice.domain.exception.order.OrderNotFoundException
import com.example.foodservice.domain.exception.payment.PaymentNotFoundException
import com.example.foodservice.service.OrderService
import com.example.foodservice.web.dto.OrderDto
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/order/")
@CrossOrigin(origins = ["http://localhost:8080/"])
class OrderController(
    private val orderService: OrderService
) {

    private val log = LoggerFactory.getLogger(javaClass)

    @PostMapping
    fun createOrder(@RequestBody orderDto: OrderDto): ResponseEntity<String>{
        return try{
            val id = orderService.save(
                userId = orderDto.userId,
                addressId = orderDto.addressId,
                orderDate = orderDto.orderDate,
                paymentId = orderDto.paymentId,
                cartId = orderDto.cartId
            )
            ResponseEntity.ok().body(id.toString())
        } catch (e: UserNotFoundException){
            log.error(e.message)
            ResponseEntity.badRequest().body(e.message)
        } catch (e: AddressNotFoundException){
            log.error(e.message)
            ResponseEntity.badRequest().body(e.message)
        } catch (e: PaymentNotFoundException){
            log.error(e.message)
            ResponseEntity.badRequest().body(e.message)
        }  catch (e: CartNotFoundException){
            log.error(e.message)
            ResponseEntity.badRequest().body(e.message)
        }

    }


    @GetMapping("/{orderId}")
    fun getOrder(@PathVariable orderId: Int): ResponseEntity<Order>{
        return try{
            val order = orderService.getOrder(orderId = orderId)
            ResponseEntity.ok().body(order)
        } catch (e: OrderNotFoundException){
            log.error(e.message)
            ResponseEntity.badRequest().build()
        }
    }

}