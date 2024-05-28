package com.example.ecommerce.model.cart.cartProducts

data class AvailableState(
    val Disabled: Boolean,
    val Group: Any,
    val Selected: Boolean,
    val Text: String,
    val Value: String
)