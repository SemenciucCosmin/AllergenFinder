package com.example.allergenfinder.data.datasource.api.model

import com.squareup.moshi.Json

data class ProductDto(
    @field:Json(name = "code")
    val code: String
)
