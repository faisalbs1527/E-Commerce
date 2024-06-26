package com.example.ecommerce.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ecommerce.database.dbmodel.OrderEntity

@Dao
interface ordersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveOrderInfo(orderInfo : OrderEntity)

    @Query("SELECT * FROM orderInfo where email = :email")
    suspend fun getOrderInfo(email: String) : List<OrderEntity>
}