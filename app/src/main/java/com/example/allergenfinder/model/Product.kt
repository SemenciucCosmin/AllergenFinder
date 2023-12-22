package com.example.allergenfinder.model

import androidx.compose.ui.graphics.Color

data class Product(
    val barcode: String,
    val allergens: List<String>,
    val name: String,
    val brand: String,
    val countries: List<String>,
    val imageUrl: String,
    val ingredients: List<Ingredient>,
    val ingredientsText: String,
    val nutriments: Nutriments,
    val nutriScore: NutriScore,
    val quantity: String,
)

data class Ingredient(
    val name: String,
    val percentEstimate: Float,
    val warningColor: Color = Color.Gray,
) {
    val formattedText: String
        get() = "$name $percentEstimate%"
}

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
