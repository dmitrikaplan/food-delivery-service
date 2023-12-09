package com.example.foodservice.service.impl

import com.example.authservice.domain.exception.UserNotFoundException
import com.example.domain.repository.UserRepository
import com.example.foodservice.domain.entity.Cart
import com.example.foodservice.repository.CartRepository
import com.example.foodservice.service.CartService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CartServiceImpl(
    private val cartRepository: CartRepository,
    private val userRepository: UserRepository
): CartService {
    override fun create(userId: Int): Int {
        val user = userRepository.findUserById(userId)
            ?: throw UserNotFoundException()

        val cart = Cart().apply { this.user = user }
        return cartRepository.save(cart).cartId!!
    }
}