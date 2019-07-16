package com.diegaspar.mvvm_kotlin.model.persistence

import androidx.room.*


@Dao
interface PostDao {

    @Query("SELECT * FROM Post")
    fun findUsersByNameAndLastName(): List<PostDB>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(postDB: PostDB)

    @Delete
    fun delete(postDB: PostDB)

}