package com.example.pawel.simplegallery.tools

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

class SimpleRecycleViewAdapter<B : ViewDataBinding, in T>(
        private val items: List<T>,
        private val layout: Int,
        private val function: B.(item: T) -> Unit)
    : RecyclerView.Adapter<SimpleRecycleViewAdapter.SimpleViewHolder<B>>() {

    class SimpleViewHolder<out B : ViewDataBinding>(val binding: B) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleViewHolder<B> {
        val inflater = LayoutInflater.from(parent.context)
        val holder = DataBindingUtil.inflate<B>(inflater, layout, parent, false)
        return SimpleViewHolder(holder)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: SimpleViewHolder<B>, position: Int) {
        function(holder.binding, items[position])
    }
}


class LiveDataRecycleViewAdapter<B : ViewDataBinding, T>(
        private val items: LiveData<List<T>>,
        private val layout: Int,
        private val function: B.(item: T) -> Unit)
    : RecyclerView.Adapter<SimpleRecycleViewAdapter.SimpleViewHolder<B>>() {

    private val observer = Observer<List<T>> { this@LiveDataRecycleViewAdapter.notifyDataSetChanged() }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        items.observeForever(observer)
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        items.removeObserver(observer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleRecycleViewAdapter.SimpleViewHolder<B> {
        val inflater = LayoutInflater.from(parent.context)
        val holder = DataBindingUtil.inflate<B>(inflater, layout, parent, false)
        return SimpleRecycleViewAdapter.SimpleViewHolder(holder)
    }

    override fun getItemCount() = items.value?.size ?: 0

    override fun onBindViewHolder(holder: SimpleRecycleViewAdapter.SimpleViewHolder<B>, position: Int) {
        items.value?.let {
            function(holder.binding, it[position])
        }
    }



}