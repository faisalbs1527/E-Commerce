package com.example.ecommerce.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.ecommerce.database.converter.Converters
import com.example.ecommerce.database.dao.ProductDao
import com.example.ecommerce.database.dbmodel.ProductEntity

@Database(
    entities = [ProductEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productdao(): ProductDao

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