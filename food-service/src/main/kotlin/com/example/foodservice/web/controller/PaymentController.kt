package com.example.foodservice.web.controller

import com.example.domain.exception.UserNotFoundException
import com.example.foodservice.service.PaymentService
import com.example.foodservice.web.converter.PaymentConverter
import com.example.foodservice.web.dto.PaymentDto
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/payment/")
@CrossOrigin(origins = ["http://localhost:8080/"])
class PaymentController(
    private val paymentService: PaymentService
) {

    private val log = LoggerFactory.getLogger(javaClass)

    @PostMapping
    fun createCart(@RequestBody payment: PaymentDto): ResponseEntity<Int> {
        return try {
            ResponseEntity.ok().body(paymentService.createPayment(payment.userId, payment.cardNumber, payment.expiryDate))
        } catch (e: UserNotFoundException) {
            log.error(e.message)
            ResponseEntity.badRequest().build()
        }
    }

    @GetMapping("/{userId}")
    fun getOrder(@PathVariable userId: Int): ResponseEntity<List<PaymentDto>>{
        return try{
            val order = paymentService.getPaymentsByUserId(userId = userId).map(PaymentConverter::toDto)
            ResponseEntity.ok().body(order)
        } catch (e: UserNotFoundException){
            log.error(e.message)
            ResponseEntity.badRequest().build()
        }
    }
}