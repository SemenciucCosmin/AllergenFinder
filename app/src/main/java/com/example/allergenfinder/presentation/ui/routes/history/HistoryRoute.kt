package com.example.allergenfinder.presentation.ui.routes.history

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
fun HistoryRoute() {
    val productViewModel = koinViewModel<ProductViewModel>()
    val productUiState by productViewModel.productUiState.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) { productViewModel.getRecentProducts() }
    val error = productUiState.error
    val last = productUiState.recentProducts.lastOrNull()

    when {
        productUiState.isLoading -> LoadingScreen()
        error != null -> ErrorScreen(error.messageId)
        productUiState.recentProducts.isNotEmpty() -> last?.let { ProductScreen(last) }
    }
}
