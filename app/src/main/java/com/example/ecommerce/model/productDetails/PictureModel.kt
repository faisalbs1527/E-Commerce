package com.example.ecommerce.model.productDetails

import com.example.ecommerce.model.productDetails.CustomPropertiesXXX

data class PictureModel(
    val AlternateText: String,
    val CustomProperties: CustomPropertiesXXX,
    val FullSizeImageUrl: String,
    val ImageUrl: String,
    val ThumbImageUrl: String,
    val Title: String
)