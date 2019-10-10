package com.diegaspar.mvvm_kotlin.presentation.ui

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.diegaspar.mvvm_kotlin.R
import com.diegaspar.mvvm_kotlin.base.BindingActivity
import com.diegaspar.mvvm_kotlin.databinding.ActivityMainBinding
import com.diegaspar.mvvm_kotlin.presentation.viewmodel.MainViewModel
import com.diegaspar.mvvm_kotlin.presentation.viewmodel.state.*
import org.jetbrains.anko.toast
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BindingActivity<ActivityMainBinding>(),
    PostRecyclerViewAdapter.OnItemClickListener {

    @LayoutRes
    override fun getLayoutResId() = R.layout.activity_main

    private val repositoryRecyclerViewAdapter =
        PostRecyclerViewAdapter(arrayListOf(), this)
    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind()
        observe()
    }


    private fun bind() {
        binding.viewModel = getViewModel()
        binding.postRv.layoutManager = LinearLayoutManager(this)
        binding.postRv.adapter = repositoryRecyclerViewAdapter
    }

    private fun observe() {

        viewModel.uiState.observe(this, stateUI)
    }

    private val stateUI = Observer<UIState> { state ->
        state?.let {
            when (state) {
                is DefaultState -> {

                }
                is LoadingState -> {

                }
                is ErrorState -> {
                    toast(getString(R.string.error_repo_data))
                }
                is RetrievedPostState -> {
                    repositoryRecyclerViewAdapter.replaceData(state.postList)
                }

            }
        }
    }


    override fun onItemClick(position: Int) {
        toast("onItem clicked")
    }
}
