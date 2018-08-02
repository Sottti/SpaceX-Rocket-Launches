package com.spacex.launches.spacexrocketlaunches.rocketList

import androidx.annotation.DrawableRes

data class RocketUIM(
        val id: Int,
        val name: String,
        val country: String,
        val enginesCount: String,
        @param:DrawableRes val imageResId: Int)
