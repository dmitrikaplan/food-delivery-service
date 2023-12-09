package com.example.foodservice.repository

import com.example.foodservice.domain.entity.Address
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AddressRepository: JpaRepository<Address, Int> {

    fun findAddressByAddressId(addressId: Int): Address?

}