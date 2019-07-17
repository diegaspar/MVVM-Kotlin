package com.diegaspar.mvvm_kotlin.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.diegaspar.mvvm_kotlin.base.RxViewModel
import com.diegaspar.mvvm_kotlin.extensions.with
import com.diegaspar.mvvm_kotlin.model.PostsRepository
import com.diegaspar.mvvm_kotlin.model.persistence.PostDB
import com.diegaspar.mvvm_kotlin.viewmodel.event.Event

class MainViewModel(private val repo: PostsRepository) : RxViewModel() {

    val isLoading = ObservableField<Boolean>()
    var posts = MutableLiveData<ArrayList<PostDB>>()
    private val _showError = MutableLiveData<Event<Boolean>>()
    val showError: LiveData<Event<Boolean>>
        get() = _showError

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
                _showError.value = Event(true)
            }
            .subscribe({
                posts.value = it as ArrayList<PostDB>?
            }, {
                _showError.value = Event(true)
            })
        )
    }
}