package com.example.allergenfinder.presentation.ui.viewmodel.model

import com.example.allergenfinder.data.network.Resource
import com.example.allergenfinder.model.Product

data class ProductUiState(
    val currentProduct: Product? = null,
    val recentProducts: List<Product> = emptyList(),
    val isLoading: Boolean = false,
    val error: Resource.Error<Any>? = null
)
