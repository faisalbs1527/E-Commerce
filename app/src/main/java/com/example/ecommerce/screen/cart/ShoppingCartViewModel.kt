package com.example.ecommerce.screen.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerce.model.cart.cartProducts.CartProducts
import com.example.ecommerce.model.cart.removeCart.FormValue
import com.example.ecommerce.model.cart.removeCart.RemoveCartRequest
import com.example.ecommerce.model.cart.updateCart.UpdateCartRequest
import com.example.ecommerce.repository.CartRepo
import kotlinx.coroutines.launch

class ShoppingCartViewModel : ViewModel() {
    private val _items : MutableLiveData<CartProducts> by lazy{
        MutableLiveData<CartProducts>()
    }
    val items : LiveData<CartProducts> get() = _items

    private val _rmvResponse : MutableLiveData<CartProducts> by lazy{
        MutableLiveData<CartProducts>()
    }
    val rmvResponse : LiveData<CartProducts> get() = _rmvResponse

    private val _updateResponse : MutableLiveData<CartProducts> by lazy{
        MutableLiveData<CartProducts>()
    }
    val updateResponse : LiveData<CartProducts> get() = _updateResponse

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

    fun removeCartProduct(productID : Int) = viewModelScope.launch {
        val request = RemoveCartRequest(
            listOf(
                FormValue(
                    Key = "removefromcart",
                    Value = "$productID"
                )
            )
        )
        val response = repository.removeCartProduct(request)

        if(response.isSuccessful){
            _rmvResponse.value = response.body()
        }
    }

    fun updateCartProduct(productID : Int, Quantity : Int) = viewModelScope.launch {
        val request = UpdateCartRequest(
            listOf(
                FormValue(
                    Key = "itemquantity$productID",
                    Value = Quantity.toString()
                )
            )
        )
        val response = repository.updateCartProduct(request)

        if(response.isSuccessful){
            _updateResponse.value = response.body()
        }
    }
}