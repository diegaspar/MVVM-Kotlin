package com.diegaspar.mvvm_kotlin

import android.app.Application
import com.diegaspar.mvvm_kotlin.di.apiModule
import org.koin.core.context.startKoin

class CustomApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin { modules(apiModule) }
    }
}