package com.example.foodservice.service.impl

import com.example.domain.exception.UserNotFoundException
import com.example.domain.repository.UserRepository
import com.example.foodservice.domain.entity.Cart
import com.example.foodservice.repository.CartRepository
import com.example.foodservice.service.CartService
import jakarta.transaction.Transactional
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class CartServiceImpl(
    private val cartRepository: CartRepository,
    private val userRepository: UserRepository
): CartService {

    private val log = LoggerFactory.getLogger(javaClass)
    @Transactional
    override fun create(username: String): Int {
        val user = userRepository.findUserByUsername(username)
            ?: throw UserNotFoundException()

        val cart = cartRepository.findCartByUserId(user.id!!)
            ?: Cart().apply { this.user = user }

        return cartRepository.saveAndFlush(cart).cartId!!
    }

    override fun delete(cartId: Int) = cartRepository.deleteById(cartId)
}