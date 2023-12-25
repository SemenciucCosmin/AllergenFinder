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
import com.example.allergenfinder.data.datasource.dao.model.AllergenEntity
import com.example.allergenfinder.data.datasource.dao.model.IngredientEntity
import com.example.allergenfinder.data.datasource.dao.model.ProductEntity
import com.example.allergenfinder.data.datasource.dao.model.ProductWithIngredients
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
        id = barcode ?: return null,
        allergens = productInfo?.allergens ?: listOf(),
        name = name ?: return null,
        brand = productInfo?.brand ?: String.BLANK,
        imageUrl = productInfo?.imageUrl ?: return null,
        ingredients = productInfo.ingredients?.mapNotNull { it.toIngredient() } ?: return null,
        nutriments = productInfo.nutriments?.toNutriments() ?: return null,
        nutriScore = NutriScore.getNutriScoreByGrade(productInfo.nutriScoreGrade),
        quantity = productInfo.quantity ?: return null,
        timestamp = System.currentTimeMillis()
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
        carbohydrates = carbohydrates?.roundToOneDecimalPlace() ?: return null,
        energyKcal = energyKcal?.roundToOneDecimalPlace() ?: return null,
        fat = fat?.roundToOneDecimalPlace() ?: return null,
        fiber = fiber?.roundToOneDecimalPlace() ?: return null,
        proteins = proteins?.roundToOneDecimalPlace() ?: return null,
        salt = salt?.roundToOneDecimalPlace() ?: return null,
        saturatedFat = saturatedFat?.roundToOneDecimalPlace() ?: return null,
        sodium = sodium?.roundToOneDecimalPlace() ?: return null,
        sugars = sugars?.roundToOneDecimalPlace() ?: return null
    )
}

fun Product.toProductWithIngredientsEntity(): ProductWithIngredients {
    return ProductWithIngredients(
        productEntity = ProductEntity(
            id = id,
            name = name,
            brand = brand,
            imageUrl = imageUrl,
            carbohydrates = nutriments.carbohydrates,
            energyKcal = nutriments.energyKcal,
            fat = nutriments.fat,
            fiber = nutriments.fiber,
            proteins = nutriments.proteins,
            salt = nutriments.salt,
            saturatedFat = nutriments.saturatedFat,
            sodium = nutriments.sodium,
            sugars = nutriments.sugars,
            nutriScoreGrade = nutriScore.grade,
            quantity = quantity,
            timestamp = timestamp
        ),
        allergens = allergens.map { AllergenEntity(it, id) },
        ingredients = ingredients.map { it.toIngredientEntity(id) }
    )
}

fun Ingredient.toIngredientEntity(productId: String): IngredientEntity {
    return IngredientEntity(
        productId = productId,
        name = name,
        percentEstimate = percentEstimate,
        isAllergen = isAllergen,
        isMatchingAllergen = isMatchingAllergen
    )
}

fun ProductWithIngredients.toProduct(): Product {
    return Product(
        id = productEntity.id,
        allergens = allergens.map { it.name },
        name = productEntity.name,
        brand = productEntity.brand,
        imageUrl = productEntity.imageUrl,
        ingredients = ingredients.map { it.toIngredient() },
        nutriments = Nutriments(
            carbohydrates = productEntity.carbohydrates,
            energyKcal = productEntity.energyKcal,
            fat = productEntity.fat,
            fiber = productEntity.fiber,
            proteins = productEntity.proteins,
            salt = productEntity.salt,
            saturatedFat = productEntity.saturatedFat,
            sodium = productEntity.sodium,
            sugars = productEntity.sugars
        ),
        nutriScore = NutriScore.getNutriScoreByGrade(productEntity.nutriScoreGrade),
        quantity = productEntity.quantity,
        timestamp = productEntity.timestamp
    )
}

fun IngredientEntity.toIngredient(): Ingredient {
    return Ingredient(
        name = name,
        percentEstimate = percentEstimate,
        isAllergen = isAllergen,
        isMatchingAllergen = isMatchingAllergen
    )
}
