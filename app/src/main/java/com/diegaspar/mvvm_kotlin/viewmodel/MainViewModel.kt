package com.diegaspar.mvvm_kotlin.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.diegaspar.mvvm_kotlin.model.OnRepositoryReadyCallback
import com.diegaspar.mvvm_kotlin.model.RepoModel
import com.diegaspar.mvvm_kotlin.uimodel.Repository

class MainViewModel : ViewModel() {
    var repoModel: RepoModel = RepoModel()

    val isLoading = ObservableField<Boolean>()

    var repositories = MutableLiveData<ArrayList<Repository>>()

    fun loadRepositories() {
        isLoading.set(true)
        repoModel.getRepositories(object : OnRepositoryReadyCallback {
            override fun onDataReady(dataRepositories: ArrayList<Repository>) {
                isLoading.set(false)
                repositories.value = dataRepositories
            }
        })
    }
}