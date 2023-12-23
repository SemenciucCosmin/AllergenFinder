package com.example.allergenfinder.presentation.ui.routes.product.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Divider
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.allergenfinder.R
import com.example.allergenfinder.common.MAX_INGREDIENTS_SECTION_HEIGHT
import com.example.allergenfinder.common.maxHeightIf
import com.example.allergenfinder.common.toPx
import com.example.allergenfinder.model.Ingredient
import com.example.allergenfinder.presentation.ui.components.IngredientChip

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun IngredientsSection(ingredients: List<Ingredient>) {
    var isExpanded by remember { mutableStateOf(false) }
    var shouldShowExpandButton by remember { mutableStateOf(false) }
    val expandIconId = if (isExpanded) R.drawable.ic_arrow_up else R.drawable.ic_arrow_down
    val secondColor = if (isExpanded) Color.Transparent else MaterialTheme.colorScheme.background
    val gradientColors = arrayOf(
        0.1f to Color.Transparent,
        1f to secondColor
    )

    Column {
        Box {
            Box(
                modifier = Modifier
                    .animateContentSize()
                    .maxHeightIf(!isExpanded, MAX_INGREDIENTS_SECTION_HEIGHT.dp)
                    .onGloballyPositioned {
                        val maximumHeight = MAX_INGREDIENTS_SECTION_HEIGHT.toPx()
                        shouldShowExpandButton = it.size.height >= maximumHeight
                    }
            ) {
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    ingredients.forEach { ingredient ->
                        IngredientChip(ingredient)
                    }
                }
            }

            if (shouldShowExpandButton) {
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .height(30.dp)
                        .background(Brush.verticalGradient(colorStops = gradientColors))
                )
            }
        }

        if (shouldShowExpandButton) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Divider(modifier = Modifier.weight(4f))

                IconButton(onClick = { isExpanded = !isExpanded }) {
                    Image(
                        painter = painterResource(id = expandIconId),
                        contentDescription = null,
                        modifier = Modifier.weight(2f)
                    )
                }

                Divider(modifier = Modifier.weight(4f))
            }
        }
    }
}
