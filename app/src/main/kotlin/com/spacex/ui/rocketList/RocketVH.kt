package com.spacex.ui.rocketList

import android.view.View
import android.view.View.OnClickListener
import androidx.recyclerview.widget.RecyclerView
import com.spacex.ui.databinding.RocketVhBinding

internal class RocketVH(
        private val viewBinding: RocketVhBinding,
        private val onRocketClickListener: OnRocketClickListener
) : RecyclerView.ViewHolder(viewBinding.root), OnClickListener {
    private val rocketDecorator: RocketUIMDecorator = RocketUIMDecorator(itemView.context)
    private var rocketId: Int = 0

    init {
        viewBinding.cardView.setOnClickListener(this)
    }

    fun onBind(rocketUIM: RocketUIM) {
        rocketId = rocketUIM.id
        viewBinding.uim = rocketDecorator.bind(rocketUIM)
    }

    override fun onClick(view: View) {
        onRocketClickListener.onClick(rocketId)
    }

    interface OnRocketClickListener {
        fun onClick(rocketId: Int)
    }
}
