package com.example.allergenfinder.model

import com.example.allergenfinder.common.PRODUCT_DATE_FORMAT
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class Product(
    val id: String,
    val allergens: List<String>,
    val name: String,
    val brand: String,
    val imageUrl: String,
    val ingredients: List<Ingredient>,
    val nutriments: Nutriments,
    val nutriScore: NutriScore,
    val quantity: String,
    val timestamp: Long
) {
    val hasAllergens: Boolean
        get() = allergens.isNotEmpty() || ingredients.map { it.isAllergen }.any { it }

    val hasMatchingAllergens: Boolean
        get() = ingredients.map { it.isMatchingAllergen }.any { it }

    val date: String
        get() {
            val dateFormat = SimpleDateFormat(PRODUCT_DATE_FORMAT, Locale.getDefault())
            return dateFormat.format(Date(timestamp))
        }
}

data class Ingredient(
    val name: String,
    val percentEstimate: Float,
    val isAllergen: Boolean = false,
    val isMatchingAllergen: Boolean = false
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
