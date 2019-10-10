package com.diegaspar.mvvm_kotlin.presentation.viewmodel.state

import com.diegaspar.mvvm_kotlin.presentation.model.PostUI

sealed class UIState

object DefaultState : UIState()
object LoadingState : UIState()
data class ErrorState(internal val errorMessage: String) : UIState()
data class RetrievedPostState(internal val postList: List<PostUI>) : UIState()