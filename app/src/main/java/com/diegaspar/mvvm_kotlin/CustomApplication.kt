package com.diegaspar.mvvm_kotlin

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.diegaspar.mvvm_kotlin.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CustomApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            printLogger() // Koin Logger
            androidContext(this@CustomApplication)
            modules(listOf(apiModule, persistenceModule, viewModelModule, networkModule, repoModule))
        }
    }

    companion object {
        lateinit var instance: CustomApplication

        fun isNetworkAvailable(): Boolean {
            val cm = instance.applicationContext
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return cm.activeNetworkInfo?.isConnected ?: false
        }
    }

}