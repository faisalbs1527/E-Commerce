package com.example.ecommerce.model.slider

import com.example.ecommerce.database.dbmodel.SliderEntity

data class Slider(
    val EntityId: Int,
    val Id: Int,
    val ImageUrl: String,
    val SliderType: Int
)
fun Slider.asEntity() = SliderEntity(
    EntityId = EntityId,
    Id = Id,
    ImageUrl = ImageUrl,
    SliderType = SliderType
)