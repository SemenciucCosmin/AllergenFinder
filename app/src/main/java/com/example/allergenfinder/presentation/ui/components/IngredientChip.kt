package com.example.allergenfinder.presentation.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.allergenfinder.model.Ingredient

@Composable
fun IngredientChip(ingredient: Ingredient) {
    val outlineColor = when {
        ingredient.isMatchingAllergen -> Color.Red
        ingredient.isAllergen -> Color.Yellow
        else -> Color.Gray
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .border(
                width = 1.dp,
                color = outlineColor,
                shape = RoundedCornerShape(30.dp)
            )
    ) {
        Text(
            text = ingredient.formattedText,
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.padding(8.dp)
        )
    }
}
