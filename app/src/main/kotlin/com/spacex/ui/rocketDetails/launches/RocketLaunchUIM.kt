package com.spacex.ui.rocketDetails.launches

import com.spacex.ui.R

data class RocketLaunchUIM(
        val missionName: String,
        val date: Long,
        val wasSuccess: Boolean,
        val videoUrl: String,
        val wikipediaUrl: String,
        val missionPatchUrl: String) {
    fun getSuccessLabelColorResId(): Int {
        return if (wasSuccess) R.color.green_300 else R.color.red_300
    }
}