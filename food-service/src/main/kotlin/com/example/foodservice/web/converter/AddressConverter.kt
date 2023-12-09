package com.example.foodservice.web.converter

import com.example.foodservice.domain.entity.Address
import com.example.foodservice.web.dto.AddressDto

object AddressConverter : Converter<AddressDto, Address> {
    override fun toDto(entity: Address) = AddressDto(entity.addressId!!, entity.address)

    override fun toEntity(dto: AddressDto) = Address().apply {
        this.address = dto.address
        this.addressId = dto.addressId
    }
}