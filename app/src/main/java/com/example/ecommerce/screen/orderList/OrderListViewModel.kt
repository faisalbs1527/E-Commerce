package com.example.ecommerce.screen.orderList

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerce.database.AppDatabase
import com.example.ecommerce.database.dbmodel.OrderEntity
import com.example.ecommerce.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderListViewModel @Inject constructor(
    private val dbService: AppDatabase,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {
    private val _orders: MutableLiveData<List<OrderEntity>> by lazy {
        MutableLiveData<List<OrderEntity>>()
    }
    val orders: LiveData<List<OrderEntity>>
        get() = _orders

    fun getOrders() = viewModelScope.launch {
        val email: String? = sharedPreferences.getString("email","")
        println("Email here : $email")
        println(dbService.orderdao().getOrderInfo(email!!))
        _orders.value = dbService.orderdao().getOrderInfo(email!!)
    }

}