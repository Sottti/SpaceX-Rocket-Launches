package com.spacex.ui.rocketDetails.launches

import com.spacex.ui.R

data class RocketLaunchUIM(
        val missionName: String,
        val year: Int,
        val date: Long,
        val wasSuccess: Boolean,
        val videoUrl: String,
        val wikipediaUrl: String,
        val missionPatchUrl: String) : RocketLaunchItemUIM() {
    fun getSuccessLabelColorResId(): Int {
        return if (wasSuccess) R.color.green_300 else R.color.red_300
    }
}