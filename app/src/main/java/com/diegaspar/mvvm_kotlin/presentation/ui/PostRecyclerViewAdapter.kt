package com.diegaspar.mvvm_kotlin.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.diegaspar.mvvm_kotlin.databinding.RvItemPostBinding
import com.diegaspar.mvvm_kotlin.presentation.model.PostUI

class PostRecyclerViewAdapter(
    private var items: List<PostUI>?,
    private var listener: OnItemClickListener
) : RecyclerView.Adapter<PostRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RvItemPostBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items?.get(position), listener)

    override fun getItemCount(): Int {
        return if (items != null) {
            items!!.size
        } else 0
    }

    fun replaceData(it: List<PostUI>) {
        items = it
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    class ViewHolder(private var binding: RvItemPostBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(post: PostUI?, listener: OnItemClickListener?) {
            binding.post = post
            if (listener != null) {
                binding.root.setOnClickListener { listener.onItemClick(layoutPosition) }
            }

            binding.executePendingBindings()
        }
    }

}