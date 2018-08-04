package com.spacex.ui.rocketDetails.launches

import androidx.recyclerview.widget.DiffUtil

internal class RocketLaunchesDiffCallback(
        private val oldRockets: List<RocketLaunchItemUIM>,
        private val newRockets: List<RocketLaunchItemUIM>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldRockets.size
    }

    override fun getNewListSize(): Int {
        return newRockets.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldRockets[oldItemPosition]
        val newItem = newRockets[newItemPosition]
        return if (oldItem is RocketLaunchHeaderUIM && newItem is RocketLaunchHeaderUIM) {
            oldItem.year == newItem.year
        } else oldItem is RocketLaunchUIM && newItem is RocketLaunchUIM &&
                oldItem.missionName == newItem.missionName
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldRockets[oldItemPosition] == newRockets[newItemPosition]
    }
}
