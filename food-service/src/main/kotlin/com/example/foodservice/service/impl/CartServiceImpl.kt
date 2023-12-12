package com.example.foodservice.service.impl

import com.example.domain.exception.UserNotFoundException
import com.example.domain.repository.UserRepository
import com.example.foodservice.domain.entity.Cart
import com.example.foodservice.repository.CartRepository
import com.example.foodservice.service.CartService
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class CartServiceImpl(
    private val cartRepository: CartRepository,
    private val userRepository: UserRepository
): CartService {

    @Transactional
    override fun create(username: String): Int {
        val user = userRepository.findUserByUsername(username)
            ?: throw UserNotFoundException()

        val cart = Cart().apply { this.user = user }
        return cartRepository.save(cart).cartId!!
    }

    override fun delete(cartId: Int) = cartRepository.deleteById(cartId)
}