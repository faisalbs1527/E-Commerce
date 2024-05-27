package com.example.ecommerce.repository

import com.example.ecommerce.model.authentication.Login
import com.example.ecommerce.model.authentication.LoginResponse
import com.example.ecommerce.network.ApiClient
import com.example.ecommerce.network.AuthenticationApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class LoginRepo() {
    val apiService = ApiClient.getRetrofit().create(AuthenticationApi::class.java)

    suspend fun userLogin(logInRequest : Login) : Response<LoginResponse> = withContext(Dispatchers.IO){
        return@withContext apiService.userLogin(logInRequest)
    }
}