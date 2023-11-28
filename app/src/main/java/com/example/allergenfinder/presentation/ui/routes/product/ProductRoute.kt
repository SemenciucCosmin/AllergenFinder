package com.example.allergenfinder.presentation.ui.routes.product

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.allergenfinder.presentation.ui.viewmodel.ProductViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProductRoute(code: String) {
    val productViewModel = koinViewModel<ProductViewModel>()
    val product by productViewModel.product.collectAsStateWithLifecycle()
    productViewModel.onScanned(code)

    Text(
        text = product?.brands ?: "Not found",
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp)
    )
}
