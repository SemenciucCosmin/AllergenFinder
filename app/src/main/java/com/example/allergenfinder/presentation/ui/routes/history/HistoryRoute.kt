package com.example.allergenfinder.presentation.ui.routes.history

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.allergenfinder.R
import com.example.allergenfinder.model.Product
import com.example.allergenfinder.presentation.ui.components.ErrorScreen
import com.example.allergenfinder.presentation.ui.components.LoadingScreen
import com.example.allergenfinder.presentation.ui.routes.history.components.HistoryScreen
import com.example.allergenfinder.presentation.ui.routes.product.components.ProductScreen
import com.example.allergenfinder.presentation.ui.viewmodel.ProductViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryRoute() {
    val modalBottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showBottomSheet by remember { mutableStateOf(false) }
    var selectedProduct by remember { mutableStateOf<Product?>(null) }
    val coroutineScope = rememberCoroutineScope()
    val productViewModel = koinViewModel<ProductViewModel>()
    val productUiState by productViewModel.productUiState.collectAsStateWithLifecycle()
    val error = productUiState.error
    LaunchedEffect(Unit) { productViewModel.getRecentProducts() }

    when {
        productUiState.isLoading -> LoadingScreen()

        error != null -> ErrorScreen(error.messageId)

        productUiState.recentProducts.isEmpty() -> Column(
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(R.drawable.ic_empty_list),
                contentDescription = null,
                modifier = Modifier.size(50.dp)
            )

            Text(
                text = stringResource(R.string.lbl_no_recent_products),
                style = MaterialTheme.typography.titleMedium
            )
        }

        else -> HistoryScreen(
            products = productUiState.recentProducts,
            onProductClick = { product ->
                selectedProduct = product
                coroutineScope.launch {
                    showBottomSheet = true
                }
            }
        )
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            sheetState = modalBottomSheetState,
            onDismissRequest = { showBottomSheet = false }
        ) {
            selectedProduct?.let { ProductScreen(product = it) }
        }
    }
}
