package com.example.allergenfinder.common

import android.app.Activity
import android.content.Intent
import android.content.res.Resources
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.layout.heightIn
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.allergenfinder.data.datasource.api.model.IngredientDto
import com.example.allergenfinder.data.datasource.api.model.NutrimentsDto
import com.example.allergenfinder.data.datasource.api.model.ProductDto
import com.example.allergenfinder.model.Ingredient
import com.example.allergenfinder.model.NutriScore
import com.example.allergenfinder.model.Nutriments
import com.example.allergenfinder.model.Product
import kotlin.math.roundToInt

val String.Companion.BLANK: String
    get() = ""

val String.Companion.SEMICOLON: String
    get() = ":"

val Color.Companion.TransparentWhite: Color
    get() = Color(0x77FFFFFF)

fun Float.roundToOneDecimalPlace() = (this * 10).roundToInt() / 10f

fun Int.toPx(): Float = this * Resources.getSystem().displayMetrics.density

fun Modifier.maxHeightIf(condition: Boolean, maxHeight: Dp): Modifier {
    return if (condition) this.heightIn(0.dp, maxHeight) else this
}

fun Activity.openAppSettings() {
    Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        Uri.fromParts("package", packageName, null)
    ).also(::startActivity)
}

fun ProductDto.toProduct(): Product? {
    val name = if (productInfo?.productNameEn?.isNotBlank() == true) {
        productInfo.productNameEn
    } else {
        productInfo?.productName
    }

    return Product(
        barcode = barcode ?: return null,
        allergens = productInfo?.allergens ?: listOf(),
        name = name ?: return null,
        brand = productInfo?.brand ?: String.BLANK,
        countries = productInfo?.countries ?: listOf(),
        imageUrl = productInfo?.imageUrl ?: return null,
        ingredients = productInfo.ingredients?.mapNotNull { it.toIngredient() } ?: return null,
        ingredientsText = productInfo.ingredientsText ?: return null,
        nutriments = productInfo.nutriments?.toNutriments() ?: return null,
        nutriScore = NutriScore.getNutriScoreByGrade(productInfo.nutriScoreGrade) ?: return null,
        quantity = productInfo.quantity ?: return null
    )
}

fun IngredientDto.toIngredient(): Ingredient? {
    val percent = percentEstimate ?: return null
    return Ingredient(
        name = id?.substringAfter(String.SEMICOLON) ?: return null,
        percentEstimate = percent.roundToOneDecimalPlace()
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
