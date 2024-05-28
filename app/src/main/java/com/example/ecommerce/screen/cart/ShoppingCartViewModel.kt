package com.example.ecommerce.screen.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerce.model.cart.cartProducts.CartProducts
import com.example.ecommerce.repository.CartRepo
import kotlinx.coroutines.launch

class ShoppingCartViewModel : ViewModel() {
    private val _items : MutableLiveData<CartProducts> by lazy{
        MutableLiveData<CartProducts>()
    }
    val items : LiveData<CartProducts> get() = _items

    private val _showMessage : MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val showMessage : LiveData<String> get() = _showMessage

    private val repository = CartRepo()

    fun fetchCartProducts() = viewModelScope.launch {
        val response = repository.getCartProducts()

        if(response.isSuccessful){
            _items.value = response.body()
        }
    }
}