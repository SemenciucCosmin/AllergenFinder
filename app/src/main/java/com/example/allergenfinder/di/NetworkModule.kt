package com.example.allergenfinder.di

import com.example.allergenfinder.data.datasource.api.ProductApiService
import com.example.allergenfinder.data.network.NetworkResponseCallAdapterFactory
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://world.openfoodfacts.org")
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(NetworkResponseCallAdapterFactory())
            .build()
    }
    factory { get<Retrofit>().create(ProductApiService::class.java) }
}
