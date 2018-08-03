package com.spacex.ui.rocketList

import androidx.recyclerview.widget.DiffUtil

internal class RocketsDiffCallback(
        private val oldRockets: List<RocketUIM>,
        private val newRockets: List<RocketUIM>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldRockets.size
    }

    override fun getNewListSize(): Int {
        return newRockets.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldRockets[oldItemPosition].id == newRockets[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldRockets[oldItemPosition] == newRockets[newItemPosition]
    }
}
