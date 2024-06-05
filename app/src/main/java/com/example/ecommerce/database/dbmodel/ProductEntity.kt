package com.example.ecommerce.database.dbmodel

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.ecommerce.model.featureProducts.CustomProperties
import com.example.ecommerce.model.featureProducts.Data
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

fun ProductEntity.toProductData() = Data(
    CustomProperties = this.CustomProperties,
    FullDescription = this.FullDescription,
    Id = this.Id,
    MarkAsNew = this.MarkAsNew,
    Name = this.Name,
    PictureModels = this.PictureModels,
    ProductPrice = this.ProductPrice,
    ProductSpecificationModel = this.ProductSpecificationModel,
    ProductType = this.ProductType,
    ReviewOverviewModel = this.ReviewOverviewModel,
    SeName = this.SeName,
    ShortDescription = this.ShortDescription,
    Sku = this.Sku
)

