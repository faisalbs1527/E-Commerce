package com.example.ecommerce.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.ecommerce.database.converter.Converters
import com.example.ecommerce.database.converter.CustomConverters
import com.example.ecommerce.database.dao.CategoryDao
import com.example.ecommerce.database.dao.ProductDao
import com.example.ecommerce.database.dao.SliderDao
import com.example.ecommerce.database.dbmodel.CategoryEntity
import com.example.ecommerce.database.dbmodel.ProductEntity
import com.example.ecommerce.database.dbmodel.SliderEntity

@Database(
    entities = [
        ProductEntity::class,
        CategoryEntity::class,
        SliderEntity::class],
    version = 1
)
@TypeConverters(Converters::class,CustomConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productdao(): ProductDao
    abstract fun categorydao(): CategoryDao
    abstract fun sliderdao(): SliderDao

    companion object{
        operator fun invoke(context: Context) = buildDatabase(context)

        private fun buildDatabase(context: Context): AppDatabase {
            val databaseBuilder = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "AppDatabase.db"
            )
            return databaseBuilder.build()
        }
    }
}