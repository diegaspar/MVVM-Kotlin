package com.diegaspar.mvvm_kotlin

import android.app.Application
import com.diegaspar.mvvm_kotlin.di.apiModule
import com.diegaspar.mvvm_kotlin.di.persistenceModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CustomApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            printLogger() // Koin Logger
            androidContext(this@CustomApplication)
            modules(listOf(apiModule, persistenceModule))
        }
    }
}