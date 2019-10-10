package com.diegaspar.mvvm_kotlin.data.repo

import com.diegaspar.mvvm_kotlin.data.datasource.PostNetworkDataSource
import com.diegaspar.mvvm_kotlin.data.datasource.PostPersistenceDataSource
import com.diegaspar.mvvm_kotlin.data.persistence.PostDB
import com.diegaspar.mvvm_kotlin.data.repo.PostRepo
import io.reactivex.Single

class PostRepoImpl(
    private val networkDataSource: PostNetworkDataSource,
    private val postPersistenceDataSource: PostPersistenceDataSource
) : PostRepo {
    override fun getPostList(): Single<List<PostDB>> {
        //TODO do the magic here
    }
}