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
import com.example.allergenfinder.model.NutriScore
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
    return Product(
        barcode = barcode ?: return null,
        allergens = productInfo?.allergens ?: listOf(),
        productName = productInfo?.productName ?: return null,
        brand = productInfo.brand ?: String.BLANK,
        countries = productInfo.countries ?: listOf(),
        imageUrl = productInfo.imageUrl ?: return null,
        ingredients = productInfo.ingredients?.mapNotNull { it.toIngredient() } ?: return null,
        ingredientsText = productInfo.ingredientsText ?: return null,
        ingredientsAllergensText = productInfo.ingredientsAllergensText ?: return null,
        nutriments = productInfo.nutriments?.toNutriments() ?: return null,
        nutriScore = NutriScore.getNutriScoreByGrade(productInfo.nutriScoreGrade) ?: return null,
        quantity = productInfo.quantity ?: return null
    )
}

fun IngredientDto.toIngredient(): Ingredient? {
    return Ingredient(
        id = id ?: return null,
        text = text ?: return null,
        percentEstimate = percentEstimate ?: return null
    )
}

fun NutrimentsDto.toNutriments(): Nutriments? {
    return Nutriments(
        carbohydrates = carbohydrates ?: return null,
        energyKcal = energyKcal ?: return null,
        fat = fat ?: return null,
        fiber = fiber ?: return null,
        proteins = proteins ?: return null,
        salt = salt ?: return null,
        saturatedFat = saturatedFat ?: return null,
        sodium = sodium ?: return null,
        sugars = sugars ?: return null
    )
}
