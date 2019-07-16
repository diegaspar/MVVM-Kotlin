package com.diegaspar.mvvm_kotlin.model.network.api

import com.diegaspar.mvvm_kotlin.model.network.response.PostResponse
import io.reactivex.Single
import retrofit2.http.GET

interface PostApi {
    @GET("/posts")
    fun getPosts(): Single<PostResponse>
}