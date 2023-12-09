package com.example.foodservice.service.impl

import com.example.domain.exception.UserNotFoundException
import com.example.domain.repository.UserRepository
import com.example.foodservice.domain.entity.Order
import com.example.foodservice.domain.exception.address.AddressNotFoundException
import com.example.foodservice.domain.exception.cart.CartNotFoundException
import com.example.foodservice.domain.exception.order.OrderNotFoundException
import com.example.foodservice.domain.exception.payment.PaymentNotFoundException
import com.example.foodservice.repository.*
import com.example.foodservice.service.OrderService
import com.example.foodservice.web.dto.OrderState
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class OrderServiceImpl(
    private val orderRepository: OrderRepository,
    private val userRepository: UserRepository,
    private val addressRepository: AddressRepository,
    private val paymentRepository: PaymentRepository,
    private val cartRepository: CartRepository,
    private val cartDetailRepository: CartDetailRepository
): OrderService {
    override fun save(
        userId: Int,
        addressId: Int,
        orderDate: LocalDateTime,
        paymentId: Int,
        cartId: Int
    ): Int {
        val user = userRepository.findUserById(userId)
            ?: throw UserNotFoundException()

        val address = addressRepository.findAddressByAddressId(addressId)
            ?: throw AddressNotFoundException()

        val payment = paymentRepository.findPaymentByPaymentId(paymentId)
            ?: throw PaymentNotFoundException()

        val cart = cartRepository.findCartByCartId(cartId)
            ?: throw CartNotFoundException()

        val fullPrice = computeFullPrice(cartId)

        val order = Order().apply {
            this.address = address
            this.user = user
            this.payment = payment
            this.cart = cart
            this.orderDate = orderDate
            this.state = OrderState.IN_PROGRESS
            this.fullPrice = fullPrice
        }

        return orderRepository.save(order).orderId!!
    }

    override fun getOrder(orderId: Int): Order {
        return orderRepository.findByIdOrNull(orderId)
            ?: throw OrderNotFoundException()
    }

    private fun computeFullPrice(cartId: Int): Int {
        return cartDetailRepository.findAllByCartId(cartId)
            .sumOf { it.quantity * it.food.price!! }
    }
}