package com.diegaspar.mvvm_kotlin.model

import com.diegaspar.mvvm_kotlin.extensions.ioThread
import com.diegaspar.mvvm_kotlin.extensions.with
import com.diegaspar.mvvm_kotlin.model.network.api.PostApi
import com.diegaspar.mvvm_kotlin.model.network.response.Post
import com.diegaspar.mvvm_kotlin.model.persistence.PostDB
import com.diegaspar.mvvm_kotlin.model.persistence.PostDao
import io.reactivex.Completable
import io.reactivex.Observable

class PostsRepository(private val api: PostApi, private val dao: PostDao) : PostsDataSource {

    override fun getPosts(): Observable<List<PostDB>> {

        return Observable.create { emitter ->
            ioThread {
                if (dao.findAllPosts().isNullOrEmpty()) {
                    api.getPosts().with()
                        .subscribe({
                            saveAllPosts(it).with()
                                .doOnComplete {
                                    emitter.onNext(PostDB.mapList(it))
                                    emitter.onComplete()
                                }.subscribe()
                        }, {
                            emitter.onError(it)
                        })

                } else {
                    emitter.onNext(dao.findAllPosts())
                    emitter.onComplete()
                }
            }

        }
    }

    private fun saveAllPosts(postList: List<Post>): Completable = Completable.fromCallable {
        dao.insertList(PostDB.mapList(postList) as ArrayList<PostDB>)
    }

}