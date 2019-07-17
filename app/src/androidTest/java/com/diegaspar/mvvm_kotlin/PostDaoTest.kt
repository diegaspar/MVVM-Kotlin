package com.diegaspar.mvvm_kotlin

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.diegaspar.mvvm_kotlin.model.persistence.AppDataBase
import com.diegaspar.mvvm_kotlin.model.persistence.PostDB
import com.diegaspar.mvvm_kotlin.model.persistence.PostDao
import io.reactivex.internal.util.NotificationLite.getValue
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class PostDaoTest {

    private lateinit var postDao: PostDao
    private lateinit var db: AppDataBase
    private lateinit var postDB: PostDB
    private lateinit var postDB2: PostDB

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDataBase::class.java
        ).build()
        postDao = db.getPostDao()
        postDB = PostDB(1, 1, "TitleTitleTitle", "BodyBodyBody")
        postDB2 = PostDB(2, 1, "TitleTitleTitle", "BodyBodyBody")
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun should_Insert_Post_Item() {
        postDao.insert(postDB)

        val postDBTest = getValue<PostDB>(postDao.findPostById(postDB.id))
        Assert.assertEquals(postDBTest.id, postDB.id)
    }

    @Test
    @Throws(Exception::class)
    fun should_Get_All_Posts() {
        postDao.insert(postDB)
        postDao.insert(postDB2)

        val postDBTest = getValue<List<PostDB>>(postDao.findAllPosts())
        Assert.assertEquals(postDBTest.size, 2)
    }

    @Test
    @Throws(Exception::class)
    fun should_Replace_Post_Post() {
        postDao.insert(postDB)
        postDao.insert(postDB)

        val postDBTest = getValue<List<PostDB>>(postDao.findAllPosts())
        Assert.assertEquals(postDBTest.size, 1)
    }

    @Test
    @Throws(Exception::class)
    fun should_Delete_Post() {
        postDao.insert(postDB)
        postDao.delete(postDB)
        val postDBTest = getValue<PostDB>(postDao.findPostById(postDB.id))
        Assert.assertNull(postDBTest)
    }

    @Test
    @Throws(Exception::class)
    fun should_Delete_All_Data() {
        postDao.insert(postDB)

        postDao.deleteAllPostData()
        Assert.assertEquals(postDao.getPostCount(), 0)
    }
}