package com.example.allergenfinder.model

data class Product(
    val barcode: String,
    val allergens: List<String>,
    val productName: String,
    val brand: String,
    val countries: List<String>,
    val imageUrl: String,
    val ingredients: List<Ingredient>,
    val ingredientsText: String,
    val ingredientsAllergensText: String,
    val nutriments: Nutriments,
    val nutritionalScoreGrade: String,
    val nutritionalScore: Int,
    val quantity: String,
)

data class Ingredient(
    val id: String,
    val text: String,
    val percentEstimate: Float,
)

data class Nutriments(
    val carbohydrates: Float,
    val energyKcal: Float,
    val fat: Float,
    val fiber: Float,
    val proteins: Float,
    val salt: Float,
    val saturatedFat: Float,
    val sodium: Float,
    val sugars: Float,
)
