package com.example.allergenfinder.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.allergenfinder.model.Nutriments

@Composable
fun NutrimentsTable(nutriments: Nutriments) {
    val shadedBackground = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f)
    val simpleBackground = MaterialTheme.colorScheme.background

    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .border(
                width = 2.dp,
                color = MaterialTheme.colorScheme.outline,
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        Row(modifier = Modifier.background(shadedBackground)) {
            TableCell(
                text = "Nutrition Facts",
                fontWeight = FontWeight.Bold
            )
            TableCell(
                text = "Per 100g",
                fontWeight = FontWeight.Bold
            )
        }
        Divider(color = MaterialTheme.colorScheme.outline)
        Row(modifier = Modifier.background(simpleBackground)) {
            TableCell(text = "Energy")
            TableCell(text = "${nutriments.energyKcal} Kcal")
        }
        Divider(color = MaterialTheme.colorScheme.outline)
        Row(modifier = Modifier.background(shadedBackground)) {
            TableCell(text = "Fat")
            TableCell(text = "${nutriments.fat}g")
        }
        Divider(color = MaterialTheme.colorScheme.outline)
        Row(modifier = Modifier.background(simpleBackground)) {
            TableCell(text = "Saturated fat")
            TableCell(text = "${nutriments.saturatedFat}g")
        }
        Divider(color = MaterialTheme.colorScheme.outline)
        Row(modifier = Modifier.background(shadedBackground)) {
            TableCell(text = "Carbohydrates")
            TableCell(text = "${nutriments.carbohydrates}g")
        }
        Divider(color = MaterialTheme.colorScheme.outline)
        Row(modifier = Modifier.background(simpleBackground)) {
            TableCell(text = "Sugars")
            TableCell(text = "${nutriments.sugars}g")
        }
        Divider(color = MaterialTheme.colorScheme.outline)
        Row(modifier = Modifier.background(shadedBackground)) {
            TableCell(text = "Fiber")
            TableCell(text = "${nutriments.fiber}g")
        }
        Divider(color = MaterialTheme.colorScheme.outline)
        Row(modifier = Modifier.background(simpleBackground)) {
            TableCell(text = "Proteins")
            TableCell(text = "${nutriments.proteins}g")
        }
        Divider(color = MaterialTheme.colorScheme.outline)
        Row(modifier = Modifier.background(shadedBackground)) {
            TableCell(text = "Salt")
            TableCell(text = "${nutriments.salt}g")
        }
    }
}
