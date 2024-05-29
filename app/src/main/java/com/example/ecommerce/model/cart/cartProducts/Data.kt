package com.example.ecommerce.model.cart.cartProducts

data class Data(
    val AnonymousPermissed: Boolean,
    val Cart: Cart,
    val EstimateShipping: EstimateShipping,
    val OrderTotals: OrderTotals,
    val SelectedCheckoutAttributes: String
)