package com.example.allergenfinder.presentation.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.allergenfinder.domain.repository.ProductRepository
import com.example.allergenfinder.permission.viewmodel.ProductUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductViewModel(private val productRepository: ProductRepository) : ViewModel() {
    private val _productUiState = MutableStateFlow<ProductUiState>(ProductUiState.Loading)
    val productUiState = _productUiState.asStateFlow()
    fun fetchProduct(barcode: String) {
        viewModelScope.launch {
            val resource = productRepository.fetchProduct(barcode)
            resource.getOrNull()?.let { product ->
                _productUiState.update { ProductUiState.Success(product) }
            }
            resource.errorOrNull()?.let { error ->
                _productUiState.update { ProductUiState.Error(error.messageId) }
            }
        }
    }
}
