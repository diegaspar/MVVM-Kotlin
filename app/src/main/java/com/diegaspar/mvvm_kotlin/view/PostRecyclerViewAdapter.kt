package com.diegaspar.mvvm_kotlin.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.diegaspar.mvvm_kotlin.databinding.RvItemPostBinding
import com.diegaspar.mvvm_kotlin.model.persistence.PostDB

class PostRecyclerViewAdapter(
    private var items: ArrayList<PostDB>?,
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

    fun replaceData(it: ArrayList<PostDB>) {
        items = it
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    class ViewHolder(private var binding: RvItemPostBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(post: PostDB?, listener: OnItemClickListener?) {
            binding.post = post
            if (listener != null) {
                binding.root.setOnClickListener { listener.onItemClick(layoutPosition) }
            }

            binding.executePendingBindings()
        }
    }

}