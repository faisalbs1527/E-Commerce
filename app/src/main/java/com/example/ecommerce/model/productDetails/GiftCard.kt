package com.example.ecommerce.model.productDetails

import com.example.ecommerce.model.productDetails.CustomPropertiesXXX

data class GiftCard(
    val CustomProperties: CustomPropertiesXXX,
    val GiftCardType: Int,
    val IsGiftCard: Boolean,
    val Message: Any,
    val RecipientEmail: Any,
    val RecipientName: Any,
    val SenderEmail: Any,
    val SenderName: Any
)