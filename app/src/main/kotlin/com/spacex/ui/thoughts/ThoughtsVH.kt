package com.spacex.ui.thoughts

import android.view.View
import android.view.View.OnClickListener
import androidx.recyclerview.widget.RecyclerView
import com.spacex.ui.databinding.ThoughtsVhBinding

internal class ThoughtsVH(
        private val viewBinding: ThoughtsVhBinding,
        private val clickListener: OnThoughtClickListener
) : RecyclerView.ViewHolder(viewBinding.root), OnClickListener {

    private lateinit var thoughtUIM: ThoughtUIM

    init {
        viewBinding.root.setOnClickListener(this)
    }

    fun onBind(thoughtUIM: ThoughtUIM) {
        this.thoughtUIM = thoughtUIM
        viewBinding.uim = thoughtUIM
    }

    override fun onClick(view: View) {
        clickListener.onClick(thoughtUIM.videoKey)
    }

    interface OnThoughtClickListener {
        fun onClick(videoKey: String)
    }
}
