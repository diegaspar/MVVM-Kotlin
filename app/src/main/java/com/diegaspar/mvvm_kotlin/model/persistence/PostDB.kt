package com.diegaspar.mvvm_kotlin.model.persistence

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.diegaspar.mvvm_kotlin.model.network.response.Post

@Entity(tableName = "Post")
data class PostDB(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "userId") val userId: Long = 0,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "body") val body: String?
) {
    companion object {
        fun map(post: Post): PostDB {
            return PostDB(
                id = post.id,
                userId = post.userId,
                title = post.title,
                body = post.body
            )
        }
    }
}