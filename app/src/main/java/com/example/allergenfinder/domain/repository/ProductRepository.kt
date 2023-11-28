package com.example.allergenfinder.domain.repository

interface ProductRepository {
    suspend fun fetchProduct(barcode: String)
}
