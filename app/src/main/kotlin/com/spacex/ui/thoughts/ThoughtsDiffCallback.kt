package com.spacex.ui.thoughts

import androidx.recyclerview.widget.DiffUtil

internal class ThoughtsDiffCallback(
        private val oldThoughts: List<ThoughtUIM>,
        private val newThoughts: List<ThoughtUIM>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldThoughts.size
    }

    override fun getNewListSize(): Int {
        return newThoughts.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldThoughts[oldItemPosition].videoKey == newThoughts[newItemPosition].videoKey
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldThoughts[oldItemPosition] == newThoughts[newItemPosition]
    }
}
