package com.exemple.myapplication.extras.rest

import com.exemple.myapplication.Product

// DTO para transferencia da response para o model
data class ProductApiResponseDTO(
    val products: List<Product>,
    val total: Int,
    val skip: Int,
    val limit: Int
)
