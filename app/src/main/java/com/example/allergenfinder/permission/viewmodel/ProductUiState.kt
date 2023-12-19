package com.example.allergenfinder.permission.viewmodel

import com.example.allergenfinder.model.Product

sealed class ProductUiState {
    data class Success(val product: Product) : ProductUiState()
    data object Loading : ProductUiState()
    data object Error : ProductUiState()
}
