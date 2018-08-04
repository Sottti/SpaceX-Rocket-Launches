package com.spacex.ui.rocketDetails

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat

internal class RocketDetailsUIMDecorator(
        private val context: Context, private val rocketDetailsUIM: RocketDetailsUIM) {

    val image: Drawable?
        get() = ContextCompat.getDrawable(context, rocketDetailsUIM.imageResId)

    val name: String
        get() = rocketDetailsUIM.name

    val description: String
        get() = rocketDetailsUIM.description
}
