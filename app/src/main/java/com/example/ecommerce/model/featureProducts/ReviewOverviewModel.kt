package com.example.ecommerce.model.featureProducts

data class ReviewOverviewModel(
    val AllowCustomerReviews: Boolean,
    val CanAddNewReview: Boolean,
    val CustomProperties: CustomProperties,
    val ProductId: Int,
    val RatingSum: Int,
    val TotalReviews: Int
)