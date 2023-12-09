package com.example.foodservice.service.impl

import com.example.foodservice.domain.entity.CartDetail
import com.example.foodservice.domain.exception.cart.CartNotFoundException
import com.example.foodservice.domain.exception.cartDetail.CartDetailNotFoundException
import com.example.foodservice.domain.exception.food.FoodNotFoundException
import com.example.foodservice.repository.CartDetailRepository
import com.example.foodservice.repository.CartRepository
import com.example.foodservice.repository.FoodRepository
import com.example.foodservice.service.CartDetailService
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class CartDetailServiceImpl(
    private val cartDetailRepository: CartDetailRepository,
    private val cartRepository: CartRepository,
    private val foodRepository: FoodRepository
): CartDetailService {

    @Transactional
    override fun save(cartId: Int, foodId: Int, quantity: Int): Int {
        val cart = cartRepository.findCartByCartId(cartId)
            ?: throw CartNotFoundException()

        val food = foodRepository.findFoodByFoodId(foodId)
            ?: throw FoodNotFoundException()

        val cartDetail = CartDetail().apply {
            this.cart = cart
            this.food = food
            this.quantity = quantity
        }

        return cartDetailRepository.save(cartDetail).cartDetailId!!
    }

    @Transactional
    override fun delete(cartDetailId: Int) {
        cartDetailRepository.deleteById(cartDetailId)
    }

    @Transactional
    override fun update(cartDetailId: Int,  quantity: Int) {
        val cartDetail = cartDetailRepository.findCartDetailByCartDetailId(cartDetailId)
            ?: throw CartDetailNotFoundException()

        cartDetail.apply { this.quantity = quantity }

        cartDetailRepository.save(cartDetail)
    }


}