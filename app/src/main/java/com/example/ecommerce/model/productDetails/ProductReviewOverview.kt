package com.example.ecommerce.model.productDetails

import com.example.ecommerce.model.productDetails.CustomPropertiesXXX

data class ProductReviewOverview(
    val AllowCustomerReviews: Boolean,
    val CanAddNewReview: Boolean,
    val CustomProperties: CustomPropertiesXXX,
    val ProductId: Int,
    val RatingSum: Int,
    val TotalReviews: Int
)