package com.example.ecommerce.model.category


import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class ReviewOverviewModel(
    val AllowCustomerReviews: Boolean,
    val CanAddNewReview: Boolean,
    val CustomProperties: CustomProperties,
    val ProductId: Int,
    val RatingSum: Int,
    val TotalReviews: Int
) : Parcelable