package com.example.allergenfinder.data.repository

import com.example.allergenfinder.common.toProduct
import com.example.allergenfinder.data.datasource.api.ProductApiService
import com.example.allergenfinder.domain.repository.ProductRepository
import com.example.allergenfinder.model.Product

class ProductRepositoryImpl(private val productApiService: ProductApiService) : ProductRepository {
    override suspend fun fetchProduct(barcode: String): Product? {
        // TODO implement this fetch
        val networkResponse = productApiService.getProductInfo(barcode)
        val productDto = networkResponse.getOrNull() ?: return null
        return productDto.toProduct()
    }
}
