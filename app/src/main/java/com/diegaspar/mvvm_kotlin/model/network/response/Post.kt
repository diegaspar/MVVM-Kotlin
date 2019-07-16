package com.diegaspar.mvvm_kotlin.model.network.response

import com.google.gson.annotations.SerializedName

data class Post(
    @SerializedName("id") val id: Long,
    @SerializedName("userId") val userId: Long,
    @SerializedName("title") val title: String,
    @SerializedName("body") val body: String
)
