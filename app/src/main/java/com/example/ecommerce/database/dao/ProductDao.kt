package com.example.ecommerce.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ecommerce.database.dbmodel.ProductEntity

@Dao
interface ProductDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveProducts(products : List<ProductEntity>)
    @Query("SELECT * FROM Products")
    suspend fun getProducts() : List<ProductEntity>
}
