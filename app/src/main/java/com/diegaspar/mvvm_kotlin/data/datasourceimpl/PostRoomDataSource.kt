package com.diegaspar.mvvm_kotlin.data.datasourceimpl

import com.diegaspar.mvvm_kotlin.data.datasource.PostPersistenceDataSource
import com.diegaspar.mvvm_kotlin.data.persistence.PostDao
import com.diegaspar.mvvm_kotlin.data.persistence.PostDB

class PostRoomDataSource(private val dao: PostDao) : PostPersistenceDataSource {
    override suspend fun getPostsFromPersistence(): List<PostDB> {
        return dao.findAllPosts()
    }

    override suspend fun savePostsToPersistence(listOfPosts: ArrayList<PostDB>) {
        dao.insertList(listOfPosts)
    }
}