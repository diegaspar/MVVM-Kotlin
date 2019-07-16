package com.diegaspar.mvvm_kotlin.di

import com.diegaspar.mvvm_kotlin.model.network.api.PostApi
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    single { get<Retrofit>().create(PostApi::class.java) }
}