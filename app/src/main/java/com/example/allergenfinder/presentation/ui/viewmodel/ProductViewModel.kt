package com.example.allergenfinder.presentation.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.allergenfinder.domain.repository.ProductRepository
import com.example.allergenfinder.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductViewModel(private val productRepository: ProductRepository): ViewModel() {
    private val _product = MutableStateFlow<Product?>(null)
    val product = _product.asStateFlow()
    fun onScanned(barcode: String) {
        viewModelScope.launch {
            val product = productRepository.fetchProduct(barcode)
            _product.update { product }
        }
    }
}
