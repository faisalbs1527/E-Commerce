package com.example.ecommerce.model.cart.updateCart

import com.example.ecommerce.model.cart.removeCart.FormValue

data class UpdateCartRequest(
    val FormValues: List<FormValue>
)