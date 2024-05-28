package com.example.ecommerce.model.cart.cartProducts

data class Item(
    val AllowItemEditing: Boolean,
    val AllowedQuantities: List<Any>,
    val AttributeInfo: String,
    val CustomProperties: CustomPropertiesXX,
    val DisableRemoval: Boolean,
    val Discount: Any,
    val DiscountValue: Double,
    val Id: Int,
    val MaximumDiscountedQty: Any,
    val Picture: Picture,
    val ProductId: Int,
    val ProductName: String,
    val ProductSeName: String,
    val Quantity: Int,
    val RecurringInfo: String,
    val RentalInfo: Any,
    val Sku: String,
    val SubTotal: String,
    val SubTotalValue: Double,
    val UnitPrice: String,
    val UnitPriceValue: Double,
    val VendorName: String,
    val Warnings: List<Any>
)