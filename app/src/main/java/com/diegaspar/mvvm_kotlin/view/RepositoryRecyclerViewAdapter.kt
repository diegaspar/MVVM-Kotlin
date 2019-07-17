package com.diegaspar.mvvm_kotlin.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.diegaspar.mvvm_kotlin.databinding.RvItemRepositoryBinding
import com.diegaspar.mvvm_kotlin.model.network.response.Post

class RepositoryRecyclerViewAdapter(
    private var items: ArrayList<Post>?,
    private var listener: OnItemClickListener
) : RecyclerView.Adapter<RepositoryRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RvItemRepositoryBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items?.get(position), listener)

    override fun getItemCount(): Int {
        return if (items != null) {
            items!!.size
        } else 0
    }

    fun replaceData(it: java.util.ArrayList<Post>) {
        items = it
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    class ViewHolder(private var binding: RvItemRepositoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(post: Post?, listener: OnItemClickListener?) {
            binding.post = post
            if (listener != null) {
                binding.root.setOnClickListener { listener.onItemClick(layoutPosition) }
            }

            binding.executePendingBindings()
        }
    }

}