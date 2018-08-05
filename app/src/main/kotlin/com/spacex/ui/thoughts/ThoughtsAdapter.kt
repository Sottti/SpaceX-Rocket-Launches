package com.spacex.ui.thoughts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.spacex.ui.databinding.ThoughtsVhBinding

internal class ThoughtsAdapter(
        private var thoughts: List<ThoughtUIM>,
        private val onThoughtClickListener: ThoughtsVH.OnThoughtClickListener
) : RecyclerView.Adapter<ThoughtsVH>() {

    fun refreshData(thoughts: List<ThoughtUIM>) {
        DiffUtil.calculateDiff(ThoughtsDiffCallback(this.thoughts, thoughts)).dispatchUpdatesTo(this)
        this.thoughts = thoughts
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThoughtsVH {
        return ThoughtsVH(
                ThoughtsVhBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                onThoughtClickListener)
    }

    override fun onBindViewHolder(holder: ThoughtsVH, position: Int) {
        holder.onBind(thoughts[position])
    }

    override fun getItemCount(): Int {
        return thoughts.size
    }
}
