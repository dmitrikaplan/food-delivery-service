package com.example.foodservice.repository

import com.example.foodservice.model.entity.AddressEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AddressRepository: JpaRepository<AddressEntity, Int> {

}