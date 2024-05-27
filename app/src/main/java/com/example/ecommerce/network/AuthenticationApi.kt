package com.example.ecommerce.network

import com.example.ecommerce.model.authentication.Login
import com.example.ecommerce.model.authentication.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationApi {
    @POST("customer/login")
    suspend fun userLogin(@Body request : Login): Response<LoginResponse>
}