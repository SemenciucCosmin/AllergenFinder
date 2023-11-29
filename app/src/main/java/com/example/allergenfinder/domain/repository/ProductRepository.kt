package com.example.allergenfinder.domain.repository

import com.example.allergenfinder.model.Product

interface ProductRepository {
    suspend fun fetchProduct(barcode: String): Product?
}