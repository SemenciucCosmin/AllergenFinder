package com.example.allergenfinder.common

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.ui.graphics.Color
import com.example.allergenfinder.data.datasource.api.model.IngredientDto
import com.example.allergenfinder.data.datasource.api.model.NutrimentsDto
import com.example.allergenfinder.data.datasource.api.model.ProductDto
import com.example.allergenfinder.model.Ingredient
import com.example.allergenfinder.model.Nutriments
import com.example.allergenfinder.model.Product

val String.Companion.BLANK: String
    get() = ""

val Color.Companion.TransparentWhite: Color
    get() = Color(0x77FFFFFF)

fun Activity.openAppSettings() {
    Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        Uri.fromParts("package", packageName, null)
    ).also(::startActivity)
}

fun ProductDto.toProduct(): Product? {
    if (barcode == null) return null
    if (productInfo == null) return null
    if (productInfo.brands == null) return null
    if (productInfo.ingredientsText == null) return null
    if (productInfo.ingredientsAllergensText == null) return null
    if (productInfo.nutritionalScoreGrade == null) return null
    if (productInfo.nutritionalScore == null) return null
    if (productInfo.quantity == null) return null
    val ingredients = productInfo.ingredients?.mapNotNull { it.toIngredient() }

    return Product(
        barcode = barcode,
        allergens = productInfo.allergens ?: listOf(),
        brands = productInfo.brands,
        countries = productInfo.countries ?: listOf(),
        imageUrl = productInfo.imageUrl,
        ingredients = ingredients ?: listOf(),
        ingredientsText = productInfo.ingredientsText,
        ingredientsAllergensText = productInfo.ingredientsAllergensText,
        nutriments = productInfo.nutriments?.toNutriments() ?: Nutriments(),
        nutritionalScoreGrade = productInfo.nutritionalScoreGrade,
        nutritionalScore = productInfo.nutritionalScore,
        quantity = productInfo.quantity
    )
}

fun IngredientDto.toIngredient(): Ingredient? {
    return Ingredient(
        id = id ?: return null,
        percentEstimate = percentEstimate ?: return null,
        text = text ?: return null
    )
}

fun NutrimentsDto.toNutriments(): Nutriments {
    return Nutriments(
        carbohydrates = carbohydrates,
        energyKcal = energyKcal,
        fat = fat,
        fiber = fiber,
        proteins = proteins,
        salt = salt,
        saturatedFat = saturatedFat,
        sodium = sodium,
        sugars = sugars
    )
}
