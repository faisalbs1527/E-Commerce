package com.example.ecommerce.database.dbmodel

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.ecommerce.model.slider.Slider

@Entity(tableName = "slider")
data class SliderEntity (
    val EntityId: Int,
    @PrimaryKey
    val Id: Int,
    val ImageUrl: String,
    val SliderType: Int
)

fun SliderEntity.toSlider() = Slider(
    EntityId = EntityId,
    Id = Id,
    ImageUrl = ImageUrl,
    SliderType = SliderType
)