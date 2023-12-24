package com.example.allergenfinder.presentation.ui.routes.history

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.allergenfinder.R
import com.example.allergenfinder.presentation.ui.components.ErrorScreen
import com.example.allergenfinder.presentation.ui.components.LoadingScreen
import com.example.allergenfinder.presentation.ui.routes.history.components.HistoryScreen
import com.example.allergenfinder.presentation.ui.viewmodel.ProductViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HistoryRoute() {
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

        else -> HistoryScreen(productUiState.recentProducts)
    }
}
