package com.example.allergenfinder.di

import com.example.allergenfinder.data.repository.ProductRepositoryImpl
import com.example.allergenfinder.domain.repository.ProductRepository
import org.koin.dsl.module

val appModule = module {
    factory<ProductRepository> { ProductRepositoryImpl(get()) }
}
