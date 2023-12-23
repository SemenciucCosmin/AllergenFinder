package com.example.allergenfinder.presentation.ui.routes.product.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.allergenfinder.R
import com.example.allergenfinder.model.Product
import com.example.allergenfinder.presentation.ui.components.OutlineGradientBox

@Composable
fun ProductScreen(product: Product) {
    val outlineColor = when {
        product.hasMatchingAllergens -> Color.Red
        product.hasAllergens -> Color.Yellow
        else -> Color.Green
    }

    OutlineGradientBox(
        cornerRadius = 16.dp,
        outlineRadius = 12.dp,
        outlineColor = outlineColor,
        modifier = Modifier.padding(10.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .padding(10.dp)
                .verticalScroll(rememberScrollState())
        ) {
            AsyncImage(
                model = product.imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                error = painterResource(R.drawable.ic_no_image),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .border(
                        width = 2.dp,
                        color = MaterialTheme.colorScheme.outline,
                        shape = RoundedCornerShape(16.dp)
                    )
            )

            Divider()

            Row(
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.weight(8f)
                )
                Text(
                    text = product.quantity,
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.weight(2f)
                )
            }

            Divider()

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = product.brand,
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.weight(7f)
                )
                Image(
                    painter = painterResource(id = product.nutriScore.imageRes),
                    contentDescription = null,
                    modifier = Modifier.weight(3f)
                )
            }

            Divider()

            IngredientsSection(ingredients = product.ingredients)

            NutrimentsTable(nutriments = product.nutriments)
        }
    }
}
