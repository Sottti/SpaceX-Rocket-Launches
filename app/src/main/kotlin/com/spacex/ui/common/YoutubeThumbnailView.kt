package com.spacex.ui.common

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.spacex.ui.R
import com.squareup.picasso.Picasso

class YoutubeThumbnailView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0) : AppCompatImageView(context, attrs, defStyleAttr) {

    init {
        scaleType = ImageView.ScaleType.CENTER_CROP
    }
}

@BindingAdapter("videoKey")
fun loadVideoThumbnail(imageView: ImageView, videoKey: String?) {
    val qualityMq = "mqdefault"
    val uriYoutubeThumbnail = "https://img.youtube.com/vi/%1\$s/%2\$s.jpg"
    if (videoKey != null) {
        val thumbnailUrl = String.format(uriYoutubeThumbnail, videoKey, qualityMq)
        Picasso.with(imageView.context)
                .load(thumbnailUrl)
                .placeholder(R.color.grey_200)
                .error(R.color.grey_200)
                .into(imageView)
    } else {
        imageView.setImageResource(R.color.grey_200)
    }
}
