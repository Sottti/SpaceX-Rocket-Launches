package com.spacex.ui.rocketList

import androidx.annotation.DrawableRes

internal data class RocketUIM(
        val id: Int,
        val name: String,
        val country: String,
        val enginesCount: String,
        @param:DrawableRes val imageResId: Int)
