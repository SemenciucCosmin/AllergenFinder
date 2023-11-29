package com.example.allergenfinder.model

data class Product(
    val barcode: String,
    val allergens: List<String> = listOf(),
    val brands: String,
    val countries: List<String> = listOf(),
    val imageUrl: String? = null,
    val ingredients: List<Ingredient> = listOf(),
    val ingredientsText: String,
    val ingredientsAllergensText: String,
    val nutriments: Nutriments = Nutriments(),
    val nutritionalScoreGrade: String,
    val nutritionalScore: Int,
    val quantity: String,
)

data class Ingredient(
    val id: String,
    val percentEstimate: Float,
    val text: String,
)

data class Nutriments(
    val carbohydrates: Float? = null,
    val energyKcal: Float? = null,
    val fat: Float? = null,
    val fiber: Float? = null,
    val proteins: Float? = null,
    val salt: Float? = null,
    val saturatedFat: Float? = null,
    val sodium: Float? = null,
    val sugars: Float? = null,
)
