package com.example.ecommerce.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ecommerce.database.dbmodel.SliderEntity

@Dao
interface SliderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveImageSliders(imageSlider : List<SliderEntity>)

    @Query("SELECT * FROM slider")
    suspend fun getImageSliders() : List<SliderEntity>
}