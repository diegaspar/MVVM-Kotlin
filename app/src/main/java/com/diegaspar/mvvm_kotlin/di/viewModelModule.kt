package com.diegaspar.mvvm_kotlin.di

import com.diegaspar.mvvm_kotlin.presentation.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}