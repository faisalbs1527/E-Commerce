package com.example.ecommerce.model.productDetails

data class Breadcrumb(
    val CategoryBreadcrumb: List<CategoryBreadcrumb>,
    val CustomProperties: CustomPropertiesXXX,
    val Enabled: Boolean,
    val ProductId: Int,
    val ProductName: String,
    val ProductSeName: String
)