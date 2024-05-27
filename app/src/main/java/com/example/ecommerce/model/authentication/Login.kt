package com.example.ecommerce.model.authentication

data class Login(
    val Data: DataX,
    val FormValues: List<Any> = emptyList(),
    val UploadPicture: UploadPicture = UploadPicture()
)