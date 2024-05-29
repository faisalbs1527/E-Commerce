package com.example.ecommerce.model

import com.example.ecommerce.database.dao.productDao

data class categoryDao(var image : Int, var name : String, var productList : ArrayList<productDao> ?= null){

}
