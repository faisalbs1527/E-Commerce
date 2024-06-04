package com.example.ecommerce.di

import android.content.Context
import com.example.ecommerce.database.AppDatabase
import com.example.ecommerce.network.ApiClient
import com.example.ecommerce.network.HomeApi
import com.example.ecommerce.network.ProductApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return ApiClient.getRetrofit()
    }

    @Provides
    @Singleton
    fun providesProductApi(retrofit: Retrofit): ProductApi{
        return retrofit.create(ProductApi::class.java)
    }

    @Provides
    @Singleton
    fun provideHomeApi(retrofit: Retrofit): HomeApi{
        return retrofit.create(HomeApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAppDB(@ApplicationContext context: Context): AppDatabase{
        return  AppDatabase(context)
    }
}