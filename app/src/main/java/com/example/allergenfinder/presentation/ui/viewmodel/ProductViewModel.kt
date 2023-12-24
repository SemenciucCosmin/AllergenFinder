package com.example.allergenfinder.presentation.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.allergenfinder.domain.repository.ProductRepository
import com.example.allergenfinder.presentation.ui.viewmodel.model.ProductUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductViewModel(private val productRepository: ProductRepository) : ViewModel() {
    private val _productUiState = MutableStateFlow(ProductUiState())
    val productUiState = _productUiState.asStateFlow()

    fun fetchProduct(barcode: String) {
        _productUiState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            val resource = productRepository.fetchProduct(barcode)
            resource.getOrNull()?.let { product ->
                _productUiState.update {
                    it.copy(
                        currentProduct = product,
                        isLoading = false
                    )
                }
            }
            resource.errorOrNull()?.let { error ->
                _productUiState.update {
                    it.copy(
                        isLoading = false,
                        error = error
                    )
                }
            }
        }
    }

    fun getRecentProducts() {
        _productUiState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            productRepository.getProducts().collect { recentProducts ->
                _productUiState.update {
                    it.copy(
                        recentProducts = recentProducts,
                        isLoading = false
                    )
                }
            }
        }
    }
}
