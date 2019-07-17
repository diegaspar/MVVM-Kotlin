package com.diegaspar.mvvm_kotlin.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.diegaspar.mvvm_kotlin.base.RxViewModel
import com.diegaspar.mvvm_kotlin.extensions.with
import com.diegaspar.mvvm_kotlin.model.network.api.PostApi
import com.diegaspar.mvvm_kotlin.model.network.response.Post
import com.diegaspar.mvvm_kotlin.model.persistence.PostDao

class MainViewModel(private val api: PostApi, private val dao: PostDao) : RxViewModel() {

    val isLoading = ObservableField<Boolean>()
    var posts = MutableLiveData<ArrayList<Post>>()

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