package com.diegaspar.mvvm_kotlin.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.diegaspar.mvvm_kotlin.base.RxViewModel
import com.diegaspar.mvvm_kotlin.domain.Post
import com.diegaspar.mvvm_kotlin.domain.PostUseCase
import com.diegaspar.mvvm_kotlin.extensions.with
import com.diegaspar.mvvm_kotlin.presentation.model.PostUI
import com.diegaspar.mvvm_kotlin.presentation.viewmodel.state.*

class MainViewModel(private val postUseCase: PostUseCase) : RxViewModel() {

    private val _uiState = MutableLiveData<UIState>().apply {
        value = DefaultState
    }

    val uiState: LiveData<UIState>
        get() = _uiState

    fun loadPosts() {
        addToDisposable(
            postUseCase.getPosts().with()
                .doOnSubscribe { handleLoadingState() }
                .doOnSuccess { handlePostList(it) }
                .doOnError { handleFailure(it) }
                .subscribe())
    }

    private fun handleLoadingState() {
        _uiState.value = LoadingState
    }

    private fun handlePostList(postList: List<Post>) {
        _uiState.value = RetrievedPostState(postList.map(postDomainToUIMapper))
    }

    private fun handleFailure(t: Throwable) {
        _uiState.value =
            ErrorState(
                t.message.toString()
            )
    }

    private val postDomainToUIMapper: (Post) -> PostUI = { postDomain ->
        PostUI(postDomain.id, postDomain.title, postDomain.body)
    }
}