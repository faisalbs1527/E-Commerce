package com.example.ecommerce.model.productDetails

import com.example.ecommerce.model.productDetails.CustomPropertiesXXX
import com.example.ecommerce.model.productDetails.Group

data class ProductSpecificationModel(
    val CustomProperties: CustomPropertiesXXX,
    val Groups: List<Group>
)