package com.example.ecommerce.database.dbmodel

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.ecommerce.model.featureProducts.CustomProperties
import com.example.ecommerce.model.featureProducts.PictureModel
import com.example.ecommerce.model.featureProducts.ProductPrice
import com.example.ecommerce.model.featureProducts.ProductSpecificationModel
import com.example.ecommerce.model.featureProducts.ReviewOverviewModel

@Entity(tableName = "Products")
data class ProductEntity(
    val CustomProperties: CustomProperties = CustomProperties(),
    val FullDescription: String,
    @PrimaryKey
    val Id: Int,
    val MarkAsNew: Boolean,
    val Name: String,
    val PictureModels: List<PictureModel>,
    val ProductPrice: ProductPrice,
    val ProductSpecificationModel: ProductSpecificationModel,
    val ProductType: Int,
    val ReviewOverviewModel: ReviewOverviewModel,
    val SeName: String,
    val ShortDescription: String,
    val Sku: String
)
