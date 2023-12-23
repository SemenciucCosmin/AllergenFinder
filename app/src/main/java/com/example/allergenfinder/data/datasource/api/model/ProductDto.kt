package com.example.allergenfinder.data.datasource.api.model

import com.squareup.moshi.Json

data class ProductDto(
    @field:Json(name = "code") val barcode: String?,
    @field:Json(name = "product") val productInfo: ProductInfoDto?,
)

data class ProductInfoDto(
    @field:Json(name = "allergens_tags") val allergens: List<String>?,
    @field:Json(name = "product_name") val productNameEn: String?,
    @field:Json(name = "product_name_en") val productName: String?,
    @field:Json(name = "brands") val brand: String?,
    @field:Json(name = "image_url") val imageUrl: String?,
    @field:Json(name = "ingredients") val ingredients: List<IngredientDto>?,
    @field:Json(name = "nutriments") val nutriments: NutrimentsDto?,
    @field:Json(name = "nutriscore_grade") val nutriScoreGrade: String?,
    @field:Json(name = "quantity") val quantity: String?,
)

data class IngredientDto(
    @field:Json(name = "id") val id: String?,
    @field:Json(name = "percent_estimate") val percentEstimate: Float?,
)

data class NutrimentsDto(
    @field:Json(name = "carbohydrates") val carbohydrates: Float?,
    @field:Json(name = "energy-kcal") val energyKcal: Float?,
    @field:Json(name = "fat") val fat: Float?,
    @field:Json(name = "fiber") val fiber: Float?,
    @field:Json(name = "proteins") val proteins: Float?,
    @field:Json(name = "salt") val salt: Float?,
    @field:Json(name = "saturated-fat") val saturatedFat: Float?,
    @field:Json(name = "sodium") val sodium: Float?,
    @field:Json(name = "sugars") val sugars: Float?,
)
