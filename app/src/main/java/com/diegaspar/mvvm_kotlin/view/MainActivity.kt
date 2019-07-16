package com.diegaspar.mvvm_kotlin.view

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.diegaspar.mvvm_kotlin.R
import com.diegaspar.mvvm_kotlin.base.BindingActivity
import com.diegaspar.mvvm_kotlin.databinding.ActivityMainBinding
import com.diegaspar.mvvm_kotlin.uimodel.Repository
import com.diegaspar.mvvm_kotlin.viewmodel.MainViewModel
import org.jetbrains.anko.toast

class MainActivity : BindingActivity<ActivityMainBinding>(), RepositoryRecyclerViewAdapter.OnItemClickListener {

    @LayoutRes
    override fun getLayoutResId() = R.layout.activity_main

    private val repositoryRecyclerViewAdapter = RepositoryRecyclerViewAdapter(arrayListOf(), this)

    private val viewModel: MainViewModel by lazy { ViewModelProviders.of(this).get(MainViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.viewModel = viewModel
        binding.executePendingBindings()

        binding.repositoryRv.layoutManager = LinearLayoutManager(this)
        binding.repositoryRv.adapter = repositoryRecyclerViewAdapter

        viewModel.repositories.observe(this,
            Observer<ArrayList<Repository>> { it?.let { repositoryRecyclerViewAdapter.replaceData(it) } })

    }

    override fun onItemClick(position: Int) {
        toast("onItem clicked")
    }
}
