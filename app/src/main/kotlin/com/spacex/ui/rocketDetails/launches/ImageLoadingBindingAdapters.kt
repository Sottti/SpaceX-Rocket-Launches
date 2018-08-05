package com.spacex.ui.rocketDetails.launches

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("imageUrl")
fun loadImage(imageView: ImageView, url: String?) {
    if (url == null || url.isEmpty()) {
        imageView.visibility = View.GONE
    } else {
        Picasso.with(imageView.context).load(url).into(imageView)
    }
}
