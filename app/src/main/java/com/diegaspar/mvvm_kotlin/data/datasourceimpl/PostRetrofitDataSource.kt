package com.diegaspar.mvvm_kotlin.data.datasourceimpl

import com.diegaspar.mvvm_kotlin.data.datasource.PostNetworkDataSource
import com.diegaspar.mvvm_kotlin.data.network.PostApi
import com.diegaspar.mvvm_kotlin.data.network.PostNetworkResponse
import io.reactivex.Single

class PostRetrofitDataSource(private val api: PostApi) : PostNetworkDataSource {
    override fun getVehiclesFromNetwork(): Single<List<PostNetworkResponse>> {
        return api.getPosts()
    }
}