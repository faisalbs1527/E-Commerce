package com.example.ecommerce.screen.authentication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerce.model.authentication.DataX
import com.example.ecommerce.model.authentication.Login
import com.example.ecommerce.model.authentication.LoginResponse
import com.example.ecommerce.repository.LoginRepo
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val _response : MutableLiveData<LoginResponse> by lazy {
        MutableLiveData<LoginResponse>()
    }

    val response : LiveData<LoginResponse> get() = _response

    private val _showMessage : MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val showMessage : LiveData<String> get() = _showMessage

    private val repository = LoginRepo()

    private fun isValid(userName : String, userPassword: String): Boolean{
        if(userName.isEmpty()||userPassword.isEmpty()){
            _showMessage.value = "Please fill up the required fields!!"
            return false
        }
        return true
    }

    fun postLogin(userEmail : String, userPassword: String) = viewModelScope.launch {
        if(!isValid(userEmail,userPassword))return@launch
        val response = repository.userLogin(
            Login(
                Data = DataX(
                    Email = userEmail,
                    Password = userPassword
                )
            )
        )
        if(response.isSuccessful){
            _response.value = response.body()
        }
        else{
            println(response.code())
            _showMessage.value = "Incorrect Credentials!!"
        }
    }
}