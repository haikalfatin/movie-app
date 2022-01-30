package com.rcyn.made.core.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.rcyn.made.core.BuildConfig
import com.rcyn.made.core.R

@BindingAdapter("app:imageUrl")
fun setImageUrl(imageView: ImageView, imageUrl: String?) {
  imageUrl?.let {
    Glide.with(imageView.context)
            .load("${BuildConfig.IMG_URL}$imageUrl")
            .placeholder(R.drawable.ic_loading)
            .error(R.drawable.ic_error)
            .into(imageView)
  }
}