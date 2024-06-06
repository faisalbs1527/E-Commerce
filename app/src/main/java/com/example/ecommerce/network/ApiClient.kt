package com.example.ecommerce.network

import com.example.ecommerce.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    companion object {
        private fun buildClient(): OkHttpClient {
            return OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
                this.level = HttpLoggingInterceptor.Level.BODY
            }).addInterceptor { chain ->
                val builder = chain.request().newBuilder()
                if (Constants.TOKEN != null) {
                    builder.addHeader("Token", Constants.TOKEN!!)
                }
                val request = builder
                    .addHeader("Content-Type", Constants.CONTENT_TYPE)
                    .addHeader("DeviceId", Constants.DEVICE_ID)
                    .addHeader("NST", Constants.DEVICE_ID)
                    .addHeader("User-Agent", Constants.USER_AGENT).build()

                chain.proceed(request)
            }.build()
        }

        private fun getBaseUrl(): String{
            if(Constants.URL_TYPE == 1){
                return Constants.BASE_URL
            }
            else{
                return Constants.CHECKOUT_URL
            }
        }

        fun getRetrofit(): Retrofit {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(getBaseUrl())
                .client(buildClient())
                .build()
        }
    }
}