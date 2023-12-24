package com.example.allergenfinder.domain.repository

import com.example.allergenfinder.data.network.Resource
import com.example.allergenfinder.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    suspend fun fetchProduct(barcode: String): Resource<Product>

    suspend fun getProducts(): Flow<List<Product>>
}
