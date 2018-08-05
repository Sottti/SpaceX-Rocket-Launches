package com.spacex.domain

data class LaunchDM(
        val missionName: String,
        val rocketId: String,
        val date: Long,
        val year: Int,
        val wasSuccessful: Boolean,
        val missionPatchLink: String,
        val videoLink: String,
        val wikipediaLink: String
)