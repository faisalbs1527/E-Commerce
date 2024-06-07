package com.example.ecommerce.screen.orderList

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
    private val dbService: AppDatabase
) : ViewModel() {
    private val _orders: MutableLiveData<List<OrderEntity>> by lazy {
        MutableLiveData<List<OrderEntity>>()
    }
    val orders: LiveData<List<OrderEntity>>
        get() = _orders

    fun getOrders() = viewModelScope.launch {
        var token = Constants.TOKEN
        if (token == null) {
            token = ""
        }
        _orders.value = dbService.orderdao().getOrderInfo(token)
    }

}