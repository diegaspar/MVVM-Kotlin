package com.diegaspar.mvvm_kotlin.view

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.diegaspar.mvvm_kotlin.R
import com.diegaspar.mvvm_kotlin.base.BindingActivity
import com.diegaspar.mvvm_kotlin.databinding.ActivityMainBinding
import com.diegaspar.mvvm_kotlin.model.persistence.PostDB
import com.diegaspar.mvvm_kotlin.viewmodel.MainViewModel
import org.jetbrains.anko.toast
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BindingActivity<ActivityMainBinding>(), PostRecyclerViewAdapter.OnItemClickListener {

    @LayoutRes
    override fun getLayoutResId() = R.layout.activity_main

    private val repositoryRecyclerViewAdapter = PostRecyclerViewAdapter(arrayListOf(), this)
    val model by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind()
        observe()
    }


    private fun bind() {
        binding.viewModel = getViewModel()
        binding.lifecycleOwner = this
        binding.executePendingBindings()

        binding.postRv.layoutManager = LinearLayoutManager(this)
        binding.postRv.adapter = repositoryRecyclerViewAdapter
    }

    private fun observe() {
        model.posts.observe(this,
            Observer<ArrayList<PostDB>> { it?.let { repositoryRecyclerViewAdapter.replaceData(it) } })

        model.showError.observe(this,
            Observer {
                it.getContentIfNotHandled()?.let {
                    // Only proceed if the event has never been handled
                    toast(getString(R.string.error_repo_data))
                }
            })
    }


    override fun onItemClick(position: Int) {
        toast("onItem clicked")
    }
}
