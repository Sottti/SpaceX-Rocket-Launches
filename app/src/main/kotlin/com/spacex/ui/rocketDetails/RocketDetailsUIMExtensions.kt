package com.spacex.ui.rocketDetails

import androidx.core.content.ContextCompat
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import com.spacex.domain.RocketDM
import com.spacex.domain.RocketLaunchDM
import com.spacex.ui.R
import com.spacex.ui.RocketLaunchesApp
import com.spacex.ui.getImageResId
import com.spacex.ui.rocketDetails.launches.RocketLaunchHeaderUIM
import com.spacex.ui.rocketDetails.launches.RocketLaunchItemUIM
import com.spacex.ui.rocketDetails.launches.RocketLaunchUIM

internal fun RocketDM.mapToUIM(launches: List<RocketLaunchDM>): RocketDetailsUIM {
    val launchesList = launches.toUIM().sortedWith(compareBy { it.date })
    return RocketDetailsUIM(
            getImageResId(stringId),
            name,
            description,
            launchesList.reversed().addYearHeaders(),
            launchesList.generateChartData()
    )
}

internal fun List<RocketLaunchDM>.toUIM(): List<RocketLaunchUIM> {
    return List(size) { i -> this[i].toUIM() }
}

internal fun List<RocketLaunchUIM>.addYearHeaders(): List<RocketLaunchItemUIM> {
    val items = ArrayList<RocketLaunchItemUIM>()
    var currentYear: Int
    var previousYear = -1

    for (i in indices) {
        val rocketLaunchUIM = get(i)
        currentYear = rocketLaunchUIM.year
        if (currentYear != previousYear) {
            items.add(RocketLaunchHeaderUIM(rocketLaunchUIM.year))
            previousYear = currentYear
        }
        items.add(rocketLaunchUIM)
    }
    return items
}

internal fun RocketLaunchDM.toUIM(): RocketLaunchUIM {
    return RocketLaunchUIM(missionName, year, date, wasSuccessful, videoLink, wikipediaLink, missionPatchLink)
}

internal fun getNumberOfLaunches(year: Int, launchesPerYear: MutableMap<Int, Int>): Int {
    return if (launchesPerYear.containsKey(year)) launchesPerYear.getValue(year) + 1 else 1
}

internal fun List<RocketLaunchUIM>.generateChartData(): LineGraphSeries<DataPoint?> {
    val launchesPerYear: MutableMap<Int, Int> = mutableMapOf()
    forEach { launch -> launchesPerYear[launch.year] = getNumberOfLaunches(launch.year, launchesPerYear) }

    val dataPoints: Array<DataPoint?> = arrayOfNulls(launchesPerYear.size)
    var addedValues = 0
    for (entry in launchesPerYear) {
        dataPoints[addedValues++] = (DataPoint(entry.key.toDouble(), entry.value.toDouble()))
    }

    val series = LineGraphSeries(dataPoints)
    series.thickness = 4
    series.isDrawDataPoints = true
    series.dataPointsRadius = 8f
    series.color = ContextCompat.getColor(RocketLaunchesApp.getInstance(), R.color.colorAccent)
    series.backgroundColor = ContextCompat.getColor(RocketLaunchesApp.getInstance(), R.color.grey_200)
    return series
}