package com.example.allergenfinder.data.repository

import com.example.allergenfinder.common.toProduct
import com.example.allergenfinder.data.datasource.api.ProductApiService
import com.example.allergenfinder.data.network.Resource
import com.example.allergenfinder.domain.repository.ProductRepository
import com.example.allergenfinder.model.Product

class ProductRepositoryImpl(private val productApiService: ProductApiService) : ProductRepository {
    override suspend fun fetchProduct(barcode: String): Resource<Product> {
        val apiResource = productApiService.getProductInfo(barcode)
        if (apiResource is Resource.Error) return apiResource.getErrorType()
        val product = apiResource.getOrNull()?.toProduct() ?: return Resource.Error.NotFound()
        return Resource.Success(product)
    }
}
