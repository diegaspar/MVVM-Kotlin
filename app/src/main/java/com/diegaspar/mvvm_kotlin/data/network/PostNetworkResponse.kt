package com.diegaspar.mvvm_kotlin.data.network

import com.google.gson.annotations.SerializedName

data class PostNetworkResponse(
    @SerializedName("id") val id: Long,
    @SerializedName("userId") val userId: Long,
    @SerializedName("title") val title: String,
    @SerializedName("body") val body: String
)
