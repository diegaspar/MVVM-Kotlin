package com.diegaspar.mvvm_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.diegaspar.mvvm_kotlin.databinding.ActivityMainBinding
import com.diegaspar.mvvm_kotlin.uimodel.Repository

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val repository = Repository("MVVM architecture Android",
            "Diego Gaspar", 1000, true)

        binding.repository = repository
        binding.executePendingBindings()
    }
}
