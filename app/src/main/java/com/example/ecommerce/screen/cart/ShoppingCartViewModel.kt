package com.example.ecommerce.screen.cart

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerce.model.cart.cartProducts.CartProducts
import com.example.ecommerce.model.cart.removeCart.FormValue
import com.example.ecommerce.model.cart.removeCart.RemoveCartRequest
import com.example.ecommerce.model.cart.updateCart.UpdateCartRequest
import com.example.ecommerce.repository.CartRepo
import com.example.ecommerce.utils.ConnectivityUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingCartViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : ViewModel() {
    private val _items: MutableLiveData<CartProducts> by lazy {
        MutableLiveData<CartProducts>()
    }
    val items: LiveData<CartProducts> get() = _items

    private val _rmvResponse: MutableLiveData<CartProducts> by lazy {
        MutableLiveData<CartProducts>()
    }
    val rmvResponse: LiveData<CartProducts> get() = _rmvResponse

    private val _updateResponse: MutableLiveData<CartProducts> by lazy {
        MutableLiveData<CartProducts>()
    }
    val updateResponse: LiveData<CartProducts> get() = _updateResponse

    private val _showMessage: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val showMessage: LiveData<String> get() = _showMessage

    private val repository = CartRepo()

    fun fetchCartProducts() = viewModelScope.launch {
        val response = repository.getCartProducts()

        if (response.isSuccessful) {
            _items.value = response.body()
            _showMessage.value = "Success"
        }
    }

    fun removeCartProduct(productID: Int) = viewModelScope.launch {
        val request = RemoveCartRequest(
            listOf(
                FormValue(
                    Key = "removefromcart",
                    Value = "$productID"
                )
            )
        )
        if (ConnectivityUtil.isNetworkAvailable(context.applicationContext)) {
            val response = repository.removeCartProduct(request)

            if (response.isSuccessful) {
                _rmvResponse.value = response.body()
                _showMessage.value = "Success"
            }
        } else {
            _showMessage.value = "No Internet Connection!!"
        }
    }

    fun updateCartProduct(productID: Int, Quantity: Int) = viewModelScope.launch {
        val request = UpdateCartRequest(
            listOf(
                FormValue(
                    Key = "itemquantity$productID",
                    Value = Quantity.toString()
                )
            )
        )
        if (ConnectivityUtil.isNetworkAvailable(context.applicationContext)) {
            val response = repository.updateCartProduct(request)

            if (response.isSuccessful) {
                _updateResponse.value = response.body()
                _showMessage.value = "Success"
            }
        }
        else{
            _showMessage.value = "No Internet Connection!!"
        }
    }
}