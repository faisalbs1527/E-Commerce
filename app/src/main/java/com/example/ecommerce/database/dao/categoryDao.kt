package com.example.ecommerce.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ecommerce.database.dbmodel.CategoryEntity
import com.example.ecommerce.database.dbmodel.ProductEntity

@Dao
interface CategoryDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCategory(category : List<CategoryEntity>)
    @Query("SELECT * FROM category")
    suspend fun getCategories() : List<CategoryEntity>
}
