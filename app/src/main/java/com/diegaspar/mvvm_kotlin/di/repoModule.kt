package com.diegaspar.mvvm_kotlin.di

import com.diegaspar.mvvm_kotlin.model.PostsRepository
import org.koin.dsl.module

val repoModule = module {
    single { PostsRepository(get(), get()) }
}