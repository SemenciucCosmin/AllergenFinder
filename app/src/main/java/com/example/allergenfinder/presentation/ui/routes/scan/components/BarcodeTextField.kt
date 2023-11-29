package com.example.allergenfinder.presentation.ui.routes.scan.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.allergenfinder.R
import com.example.allergenfinder.common.BLANK
import com.example.allergenfinder.common.TransparentWhite

@Composable
fun BarcodeTextField(modifier: Modifier, onBarcodeReceived: (String) -> Unit) {
    var barcodeText by remember { mutableStateOf(String.BLANK) }
    val iconColor = if (barcodeText.isNotBlank()) Color.White else Color.TransparentWhite

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = barcodeText,
            onValueChange = { barcodeText = it },
            singleLine = true,
            placeholder = { Text(stringResource(R.string.lbl_placeholder_enter_barcode_manually)) },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = { onBarcodeReceived(barcodeText) }),
            shape = RoundedCornerShape(24.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.TransparentWhite,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedPlaceholderColor = Color.TransparentWhite,
                unfocusedPlaceholderColor = Color.TransparentWhite
            )
        )

        IconButton(
            onClick = { onBarcodeReceived(barcodeText) },
            enabled = barcodeText.isNotBlank(),
        ) {
            Image(
                painter = painterResource(R.drawable.ic_next),
                contentDescription = null,
                colorFilter = ColorFilter.tint(iconColor),
            )
        }
    }
}
