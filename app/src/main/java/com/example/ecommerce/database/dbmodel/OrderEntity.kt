package com.example.ecommerce.database.dbmodel

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.ecommerce.model.cart.cartProducts.Item

@Entity(tableName = "orderInfo")
data class OrderEntity(
    val userToken: String,
    val totalAmount: String,
    @PrimaryKey
    val orderId: String,
    val products: List<Item>
)
