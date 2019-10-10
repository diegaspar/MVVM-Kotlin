package com.diegaspar.mvvm_kotlin.data.network

import io.reactivex.Single
import retrofit2.http.GET

interface PostApi {
    @GET("/posts")
    fun getPosts(): Single<List<PostNetworkResponse>>
}