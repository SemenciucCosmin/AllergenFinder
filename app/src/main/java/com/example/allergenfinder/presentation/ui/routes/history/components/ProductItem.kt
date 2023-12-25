package com.example.allergenfinder.presentation.ui.routes.history.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.allergenfinder.R
import com.example.allergenfinder.model.Product
import com.example.allergenfinder.presentation.ui.components.ProductMainDescription

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductItem(
    product: Product,
    onClick: () -> Unit
) {
    ElevatedCard(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .border(
                width = 2.dp,
                color = MaterialTheme.colorScheme.outline,
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        Row {
            AsyncImage(
                model = product.imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                error = painterResource(R.drawable.ic_no_image),
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(3f)
                    .clip(RoundedCornerShape(16.dp))
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(7f)
                    .padding(12.dp)
            ) {
                ProductMainDescription(
                    productName = product.name,
                    productQuantity = product.quantity,
                    productBrand = product.brand,
                    productNutriScore = product.nutriScore,
                    headerStyle = MaterialTheme.typography.titleLarge,
                    labelsStyle = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}
