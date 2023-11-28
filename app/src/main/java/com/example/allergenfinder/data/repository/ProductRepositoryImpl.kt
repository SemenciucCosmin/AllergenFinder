package com.example.allergenfinder.data.repository

import com.example.allergenfinder.data.datasource.api.ProductApiService
import com.example.allergenfinder.domain.repository.ProductRepository

class ProductRepositoryImpl(private val productApiService: ProductApiService) : ProductRepository {
    override suspend fun fetchProduct(barcode: String) {
        // TODO implement this fetch
        // val networkResponse = productApiService.getProductInfo(barcode)
    }
}
