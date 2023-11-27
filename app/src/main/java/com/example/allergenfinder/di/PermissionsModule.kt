package com.example.allergenfinder.di

import com.example.allergenfinder.permission.viewmodel.PermissionsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val permissionsModule = module {
    viewModel { PermissionsViewModel() }
}
