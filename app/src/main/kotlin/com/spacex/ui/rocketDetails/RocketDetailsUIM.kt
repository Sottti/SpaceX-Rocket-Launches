package com.spacex.ui.rocketDetails

import androidx.annotation.DrawableRes
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import com.spacex.ui.rocketDetails.launches.RocketLaunchItemUIM

internal data class RocketDetailsUIM(
        @param:DrawableRes @get:DrawableRes
        internal val imageResId: Int,
        val name: String,
        val description: String,
        val launches: List<RocketLaunchItemUIM>,
        val chartSeries: LineGraphSeries<DataPoint?>)
