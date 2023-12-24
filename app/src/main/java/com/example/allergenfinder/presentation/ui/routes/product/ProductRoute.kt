package com.example.allergenfinder.presentation.ui.routes.product

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.allergenfinder.presentation.ui.components.ErrorScreen
import com.example.allergenfinder.presentation.ui.components.LoadingScreen
import com.example.allergenfinder.presentation.ui.routes.product.components.ProductScreen
import com.example.allergenfinder.presentation.ui.viewmodel.ProductViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProductRoute(barcode: String) {
    val productViewModel = koinViewModel<ProductViewModel>()
    val productUiState by productViewModel.productUiState.collectAsStateWithLifecycle()
    val currentProduct = productUiState.currentProduct
    val error = productUiState.error
    LaunchedEffect(Unit) { productViewModel.fetchProduct(barcode) }

    when {
        productUiState.isLoading -> LoadingScreen()
        error != null -> ErrorScreen(error.messageId)
        currentProduct != null -> ProductScreen(currentProduct)
    }
}
