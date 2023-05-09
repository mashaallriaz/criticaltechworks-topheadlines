package com.criticaltechworks.topheadlines.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.ImageLoader
import coil.load
import coil.request.CachePolicy

@BindingAdapter("bind:imageUrl")
fun imageUrl(imageView: ImageView, imageUrl: String?) {
    val imgLoader = ImageLoader.Builder(imageView.context)
        .memoryCachePolicy(CachePolicy.ENABLED)
        .diskCachePolicy(CachePolicy.ENABLED)
        .build()
    imageView.load(imageUrl, imgLoader)
}