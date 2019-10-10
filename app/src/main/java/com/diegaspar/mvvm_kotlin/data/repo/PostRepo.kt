package com.diegaspar.mvvm_kotlin.data.repo

import com.diegaspar.mvvm_kotlin.data.persistence.PostDB
import io.reactivex.Single

interface PostRepo {
    fun getPostList() : Single<List<PostDB>>
}