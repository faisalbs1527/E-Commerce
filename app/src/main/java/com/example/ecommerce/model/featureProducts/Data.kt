package com.example.ecommerce.model.featureProducts

import com.example.ecommerce.database.dbmodel.ProductEntity

data class Data(
    val CustomProperties: CustomProperties,
    val FullDescription: String,
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

fun Data.asEntity() = ProductEntity(
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