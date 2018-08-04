package com.spacex.ui.rocketDetails.launches

import androidx.recyclerview.widget.DiffUtil

internal class RocketLaunchesDiffCallback(
        private val oldRockets: List<RocketLaunchUIM>,
        private val newRockets: List<RocketLaunchUIM>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldRockets.size
    }

    override fun getNewListSize(): Int {
        return newRockets.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldRockets[oldItemPosition].missionName == newRockets[newItemPosition].missionName
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldRockets[oldItemPosition] == newRockets[newItemPosition]
    }
}
