package com.diegaspar.mvvm_kotlin.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.diegaspar.mvvm_kotlin.base.RxViewModel
import com.diegaspar.mvvm_kotlin.extensions.with
import com.diegaspar.mvvm_kotlin.model.PostsRepository
import com.diegaspar.mvvm_kotlin.model.persistence.PostDB

class MainViewModel(private val repo: PostsRepository) : RxViewModel() {

    val isLoading = ObservableField<Boolean>()
    var posts = MutableLiveData<ArrayList<PostDB>>()

    fun loadPosts() {
        if (posts.value.isNullOrEmpty()) {
            retrievePostRepo()
        }
    }

    private fun retrievePostRepo() {
        addToDisposable(repo.getPosts().with()
            .doOnSubscribe { isLoading.set(true) }
            .doOnComplete { isLoading.set(false) }
            .doOnError {
                isLoading.set(false)
                //TODO handle errors with SnackBar
            }
            .subscribe({
                posts.value = it as ArrayList<PostDB>?
            }, {
                //TODO handle errors with SnackBar
            })
        )
    }
}