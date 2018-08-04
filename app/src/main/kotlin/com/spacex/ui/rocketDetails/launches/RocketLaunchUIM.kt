package com.spacex.ui.rocketDetails.launches

data class RocketLaunchUIM(
        val missionName: String,
        val date: Long,
        val wasSuccess: Boolean,
        val videoUrl: String,
        val wikipediaUrl: String,
        val missionPatchUrl: String)