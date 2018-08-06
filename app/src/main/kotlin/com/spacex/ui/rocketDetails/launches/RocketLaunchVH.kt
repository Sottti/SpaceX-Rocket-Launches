package com.spacex.ui.rocketDetails.launches

import android.view.View
import android.view.View.OnClickListener
import com.spacex.ui.databinding.RocketLaunchVhBinding

internal class RocketLaunchVH(
        private val viewBinding: RocketLaunchVhBinding,
        private val clickListener: OnRocketLaunchClickListener
) : RocketLaunchesItemVH(viewBinding.root), OnClickListener {
    private val decorator: RocketLaunchUIMDecorator = RocketLaunchUIMDecorator(itemView.context)

    init {
        viewBinding.root.setOnClickListener(this)
    }

    fun onBind(rocketLaunchUIM: RocketLaunchUIM) {
        viewBinding.uim = decorator.onBind(rocketLaunchUIM)
    }

    override fun onClick(view: View) {
        if (!decorator.videoKey.isEmpty()) {
            clickListener.onClick(decorator.videoKey)
        }
    }

    interface OnRocketLaunchClickListener {
        fun onClick(videoKey: String)
    }
}
