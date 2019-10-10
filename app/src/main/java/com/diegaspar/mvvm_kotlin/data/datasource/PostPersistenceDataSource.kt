package com.diegaspar.mvvm_kotlin.data.datasource

import com.diegaspar.mvvm_kotlin.data.persistence.PostDB

interface PostPersistenceDataSource {
    suspend fun getPostsFromPersistence(): List<PostDB>
    suspend fun savePostsToPersistence(listOfPosts: ArrayList<PostDB>)
}