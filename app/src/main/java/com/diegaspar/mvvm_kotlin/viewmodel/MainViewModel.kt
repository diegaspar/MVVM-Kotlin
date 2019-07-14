package com.diegaspar.mvvm_kotlin.viewmodel

import androidx.databinding.ObservableField
import com.diegaspar.mvvm_kotlin.model.OnDataReadyCallback
import com.diegaspar.mvvm_kotlin.model.RepoModel

class MainViewModel {
    var repoModel: RepoModel = RepoModel()

    val text = ObservableField<String>()

    val isLoading = ObservableField<Boolean>()

    fun refresh() {
        isLoading.set(true)
        repoModel.refreshData(object : OnDataReadyCallback {
            override fun onDataReady(data: String) {
                isLoading.set(false)
                text.set(data)
            }
        })
    }
}