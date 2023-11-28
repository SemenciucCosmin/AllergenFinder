package com.example.allergenfinder.di

import com.example.allergenfinder.data.repository.ProductRepositoryImpl
import com.example.allergenfinder.domain.repository.ProductRepository
import com.example.allergenfinder.presentation.ui.viewmodel.ProductViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    factory<ProductRepository> { ProductRepositoryImpl(get()) }
    viewModel { ProductViewModel(get()) }
}
