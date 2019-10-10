package com.diegaspar.mvvm_kotlin.domain

import io.reactivex.Single

interface PostUseCase {
    fun getPosts(): Single<List<Post>>
}