package com.example.ecommerce.di

import android.content.Context
import android.content.SharedPreferences
import com.example.ecommerce.database.AppDatabase
import com.example.ecommerce.network.ApiClient
import com.example.ecommerce.network.CartApi
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
    fun provideCartApi(retrofit: Retrofit): CartApi{
        return retrofit.create(CartApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAppDB(@ApplicationContext context: Context): AppDatabase{
        return  AppDatabase(context)
    }
    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context):SharedPreferences{
        return context.getSharedPreferences("LoginPrefs",Context.MODE_PRIVATE)
    }
}