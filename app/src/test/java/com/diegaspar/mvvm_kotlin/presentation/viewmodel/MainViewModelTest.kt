package com.diegaspar.mvvm_kotlin.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.diegaspar.mvvm_kotlin.model.PostsRepository
import com.diegaspar.mvvm_kotlin.data.persistence.PostDB
import com.diegaspar.mvvm_kotlin.utils.RxImmediateSchedulerRule
import com.diegaspar.mvvm_kotlin.utils.mock
import com.diegaspar.mvvm_kotlin.utils.testObserver
import com.diegaspar.mvvm_kotlin.utils.whenever
import com.google.common.truth.Truth
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNull
import org.junit.Rule
import org.junit.Test


class MainViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    private val repoMocked = mock<PostsRepository>()
    private val viewModel by lazy { MainViewModel(repoMocked) }
    private val networkError = "NetworkError"

    private val postList = listOf(
        PostDB(0, 1, "Post1Title", "Post1Body"),
        PostDB(1, 1, "Post2Title", "Post2Body")
    )

    @Test
    fun loadPost_response_okay() {
        whenever(repoMocked.getPosts()).thenReturn(Observable.just(postList))
        val liveDataUnderTest = testLiveDataPost()

        Truth.assert_()
            .that(liveDataUnderTest.observedValues)
            .isEqualTo(emptyList<PostDB>())
        viewModel.loadPosts()
        assertNull(this.viewModel.posts.value)

        val result = repoMocked.getPosts()
        val testObserver = TestObserver<List<PostDB>>()
        assertEquals(true, viewModel.isLoading.get())

        result.subscribe(testObserver)
        testObserver.assertComplete()
        testObserver.assertNoErrors()

        result.doOnNext {
            Truth.assert_()
                .that(liveDataUnderTest.observedValues)
                .isEqualTo(listOf(postList))
        }

        result.doOnComplete {
            assertEquals(false, viewModel.isLoading.get())
        }
    }


    @Test
    fun loadPost_response_error() {
        whenever(repoMocked.getPosts()).thenReturn(Observable.error(Error(networkError)))
        val liveDataUnderTest = viewModel.posts.testObserver()

        Truth.assert_()
            .that(liveDataUnderTest.observedValues)
            .isEqualTo(emptyList<PostDB>())
        viewModel.loadPosts()
        assertNull(this.viewModel.posts.value)

        val result = repoMocked.getPosts()
        val testObserver = TestObserver<List<PostDB>>()

        result.subscribe(testObserver)
        testObserver.assertErrorMessage(networkError)
    }


    private fun testLiveDataPost(): com.diegaspar.mvvm_kotlin.utils.TestObserver<ArrayList<PostDB>> {
        return viewModel.posts.testObserver()
    }
}