package com.example.pawel.simplegallery.tools

import android.databinding.BindingAdapter
import android.support.v4.widget.SwipeRefreshLayout
import android.view.View
import android.view.View.*
import android.widget.ImageView

@BindingAdapter("fromUrl")
fun ImageView.fromUrl(url: String?) {
    if (url == null) return

    GlideApp.with(this.context)
            .load(url)
            .into(this)
}

@BindingAdapter("visibleOrGone")
fun View.setVisibleOrGone(show: Boolean?) {
    visibility = if (show == true) VISIBLE else GONE
}


@BindingAdapter("onRefreshListener")
fun SwipeRefreshLayout.onRefreshListener(function: (view: SwipeRefreshLayout) -> Unit) {

    this.setOnRefreshListener {
        function(this)
    }

}