package com.diegaspar.mvvm_kotlin.data.persistence

import androidx.room.*


@Dao
interface PostDao {
    @Query("SELECT * FROM PostDatabase")
    suspend fun findAllPosts(): List<PostDB>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(posts: ArrayList<PostDB>)
}