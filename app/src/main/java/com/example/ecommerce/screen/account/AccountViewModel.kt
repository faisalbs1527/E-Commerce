package com.example.ecommerce.screen.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerce.model.account.AccountInfo
import com.example.ecommerce.repository.LoginRepo
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class AccountViewModel: ViewModel() {

    private val _response: MutableLiveData<AccountInfo> by lazy {
        MutableLiveData<AccountInfo>()
    }

    val response: LiveData<AccountInfo> get() = _response

    private val _showMessage: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val showMessage: LiveData<String> get() = _showMessage

    private val repository = LoginRepo()

    fun getAccountInfo() = viewModelScope.launch() {
        val response = repository.getUserInfo()
        if(response.isSuccessful){
            _response.value=response.body()
        }
    }
}