package com.spacex.domain

class RocketLaunchDM(
        val missionName: String,
        val date: Long,
        val year: Int,
        val wasSuccessful: Boolean,
        val missionPatch: String,
        val videoLink: String,
        val wikipediaLink: String
)