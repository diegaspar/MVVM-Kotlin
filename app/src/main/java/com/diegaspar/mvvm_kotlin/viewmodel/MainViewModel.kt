package com.diegaspar.mvvm_kotlin.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.diegaspar.mvvm_kotlin.base.RxViewModel
import com.diegaspar.mvvm_kotlin.extensions.with
import com.diegaspar.mvvm_kotlin.model.OnRepositoryReadyCallback
import com.diegaspar.mvvm_kotlin.model.RepoModel
import com.diegaspar.mvvm_kotlin.model.network.api.PostApi
import com.diegaspar.mvvm_kotlin.model.network.response.Post
import com.diegaspar.mvvm_kotlin.model.persistence.PostDao
import com.diegaspar.mvvm_kotlin.uimodel.Repository

class MainViewModel(private val api: PostApi, private val dao: PostDao) : RxViewModel() {
    var repoModel: RepoModel = RepoModel()

    val isLoading = ObservableField<Boolean>()

    var repositories = MutableLiveData<ArrayList<Repository>>()
    var posts = MutableLiveData<ArrayList<Post>>()

    fun loadRepositories() {
        isLoading.set(true)
        repoModel.getRepositories(object : OnRepositoryReadyCallback {
            override fun onDataReady(dataRepositories: ArrayList<Repository>) {
                isLoading.set(false)
                repositories.value = dataRepositories
            }
        })
    }

    fun loadPosts() {
        addToDisposable(api.getPosts().with()
            .doOnSubscribe { isLoading.set(true) }
            .doOnSuccess { isLoading.set(false) }
            .doOnError { isLoading.set(false) }
            .subscribe({
                posts.value = it as ArrayList<Post>?
            }, {
                //TODO handle errors with SnackBar
            })
        )
    }
}