package com.diegaspar.mvvm_kotlin.uimodel

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR

class Repository(repositoryName : String,
                 var repositoryOwner: String?,
                 var numberOfStars: Int?,
                 var hasIssues: Boolean = false) : BaseObservable(){

    @get:Bindable
    var repositoryName : String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.repositoryName)
        }

}