package com.diegaspar.mvvm_kotlin.domain

import com.diegaspar.mvvm_kotlin.model.PostsRepository
import io.reactivex.Single

class PostInteractor(private val postsRepository: PostsRepository) : PostUseCase {
    override fun getPosts(): Single<List<Post>> {
        postsRepository.getPosts()
    }
}