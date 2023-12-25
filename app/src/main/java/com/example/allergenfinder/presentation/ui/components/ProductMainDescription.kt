package com.example.allergenfinder.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Divider
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import com.example.allergenfinder.model.NutriScore

@Composable
fun ProductMainDescription(
    productName: String,
    productQuantity: String,
    productBrand: String,
    productNutriScore: NutriScore,
    headerStyle: TextStyle = LocalTextStyle.current,
    labelsStyle: TextStyle = LocalTextStyle.current
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = productName,
            style = headerStyle,
            modifier = Modifier.weight(8f)
        )
        Text(
            text = productQuantity,
            style = labelsStyle,
            modifier = Modifier.weight(2f)
        )
    }

    Divider()

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = productBrand,
            style = labelsStyle,
            modifier = Modifier.weight(7f)
        )
        Image(
            painter = painterResource(productNutriScore.imageRes),
            contentDescription = null,
            modifier = Modifier.weight(3f)
        )
    }
}
