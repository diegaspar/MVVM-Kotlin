package com.diegaspar.mvvm_kotlin.model.persistence

import androidx.room.*
import com.diegaspar.mvvm_kotlin.utils.Constants


@Dao
interface PostDao {

    @Query("SELECT * FROM Post")
    fun findAllPosts(): List<PostDB>

    @Query("SELECT count(*) FROM ${Constants.TABLE_POSTS}")
    fun getPostCount(): Int

    @Query("SELECT * FROM Post WHERE id = :postId")
    fun findPostById(postId: Long): PostDB

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(postDB: PostDB)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(posts: ArrayList<PostDB>)

    @Delete
    fun delete(postDB: PostDB)

    @Query("DELETE FROM ${Constants.TABLE_POSTS}")
    fun deleteAllPostData()

}