package com.diegaspar.mvvm_kotlin.di

import com.diegaspar.mvvm_kotlin.data.persistence.AppDataBase
import org.koin.dsl.module

val persistenceModule = module {
    single { AppDataBase.getInstance(get()) }
    single { get<AppDataBase>().getPostDao() }
}