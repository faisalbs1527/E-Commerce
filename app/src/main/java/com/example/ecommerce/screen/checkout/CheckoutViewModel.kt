package com.example.ecommerce.screen.checkout

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerce.model.cart.cartProducts.Item
import com.example.ecommerce.model.cart.removeCart.Cart
import com.example.ecommerce.model.cart.removeCart.FormValue
import com.example.ecommerce.model.cart.removeCart.RemoveCartRequest
import com.example.ecommerce.model.cartProduct
import com.example.ecommerce.model.checkout.checkoutResponse
import com.example.ecommerce.network.ApiClient
import com.example.ecommerce.network.CartApi
import com.example.ecommerce.repository.CartRepo
import com.example.ecommerce.utils.Constants
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class CheckoutViewModel(
    private val repository : CartRepo
) : ViewModel() {

    private val _orderStatus = MutableLiveData<checkoutResponse>()
    val orderStatus: LiveData<checkoutResponse> get() = _orderStatus

    fun checkOut() = viewModelScope.launch {
        val response = getResponse()
        if(response.isSuccessful){
            _orderStatus.value = response.body()
            val cartListResponse = repository.getCartProducts()
            if(cartListResponse.isSuccessful){
                val cartList = cartListResponse.body()?.Data?.Cart?.Items
                if (cartList != null) {
                    RemoveCartItems(cartList)
                }
            }
        }
    }

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.CHECKOUT_URL)
            .build()
    }

    suspend fun getResponse(): Response<checkoutResponse>{
        return getRetrofit().create(CartApi::class.java).placeOrder()
    }

    suspend fun RemoveCartItems(products : List<Item>){
        for(item in products){
            repository.removeCartProduct(
                RemoveCartRequest(
                    listOf(
                        FormValue(
                            Key = "removefromcart",
                            Value = "${item.Id}"
                        )
                    )
                )
            )
        }
    }
}