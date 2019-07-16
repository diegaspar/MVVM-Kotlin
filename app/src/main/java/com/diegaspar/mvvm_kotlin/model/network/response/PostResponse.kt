package com.diegaspar.mvvm_kotlin.model.network.response

import com.google.gson.annotations.SerializedName

data class PostResponse(
    @SerializedName("items") val postList: ArrayList<Post>
)
