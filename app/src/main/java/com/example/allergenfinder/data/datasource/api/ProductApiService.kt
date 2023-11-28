package com.example.allergenfinder.data.datasource.api

import com.example.allergenfinder.data.datasource.api.model.ProductDto
import com.example.allergenfinder.data.network.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApiService {
    @GET("/api/v0/product/{barcode}.json")
    suspend fun getProductInfo(@Path("barcode") barcode: String): NetworkResponse<ProductDto>
}
