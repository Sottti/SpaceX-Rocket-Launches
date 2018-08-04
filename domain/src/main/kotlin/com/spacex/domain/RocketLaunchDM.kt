package com.spacex.domain

data class RocketLaunchDM(
        val missionName: String,
        val date: Long,
        val year: Int,
        val wasSuccessful: Boolean,
        val missionPatchLink: String,
        val videoLink: String,
        val wikipediaLink: String
)