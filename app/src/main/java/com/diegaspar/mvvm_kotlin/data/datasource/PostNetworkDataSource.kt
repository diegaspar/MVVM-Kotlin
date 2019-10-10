package com.diegaspar.mvvm_kotlin.data.datasource

import com.diegaspar.mvvm_kotlin.data.network.PostNetworkResponse
import io.reactivex.Single

interface PostNetworkDataSource {
    fun getVehiclesFromNetwork(): Single<List<PostNetworkResponse>>
}