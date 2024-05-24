package com.example.ecommerce.model.productDetails

import com.example.ecommerce.model.productDetails.Data

data class ProductDetails(
    val Data: Data,
    val ErrorList: List<Any>,
    val Message: Any
)