package com.diegaspar.mvvm_kotlin.model

import com.diegaspar.mvvm_kotlin.model.persistence.PostDB
import io.reactivex.Observable

interface PostsDataSource {

    fun getPosts(): Observable<List<PostDB>>

}