package com.example.foodservice.model.converter

import com.example.foodservice.model.dto.Address
import com.example.foodservice.model.entity.AddressEntity

object AddressConverter : Converter<Address, AddressEntity> {
    override fun toDto(entity: AddressEntity) = Address(entity.addressId!!, entity.address)

    override fun toEntity(dto: Address) = AddressEntity().apply {
        // TODO
    }
}