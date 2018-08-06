package com.spacex.ui.rocketList

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.spacex.ui.R

internal class RocketUIMDecorator(private val context: Context) {
    private var rocketUIM: RocketUIM? = null

    fun bind(rocketUIM: RocketUIM): RocketUIMDecorator {
        this.rocketUIM = rocketUIM
        return this
    }

    val image: Drawable?
        get() = ContextCompat.getDrawable(context, rocketUIM!!.imageResId)

    val name: String
        get() = rocketUIM!!.name

    val details: String
        get() = context.getString(
                R.string.rocket_list_details_format, rocketUIM!!.enginesCount, rocketUIM!!.country)
}
