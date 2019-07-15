package com.diegaspar.mvvm_kotlin.model

import android.os.Handler
import com.diegaspar.mvvm_kotlin.uimodel.Repository

class RepoModel {

    fun getRepositories(onRepositoryReadyCallback: OnRepositoryReadyCallback) {
        val arrayList = ArrayList<Repository>()
        arrayList.add(Repository("First", "Owner 1", 100, false))
        arrayList.add(Repository("Second", "Owner 2", 30, true))
        arrayList.add(Repository("Third", "Owner 3", 430, false))

        Handler().postDelayed({ onRepositoryReadyCallback.onDataReady(arrayList) }, 2000)
    }
}

interface OnRepositoryReadyCallback {
    fun onDataReady(dataRepositories: ArrayList<Repository>)

}