package com.spacex.ui.rocketDetails

import com.spacex.domain.RocketDM
import com.spacex.domain.RocketLaunchDM
import com.spacex.ui.getImageResId
import com.spacex.ui.rocketDetails.launches.RocketLaunchHeaderUIM
import com.spacex.ui.rocketDetails.launches.RocketLaunchItemUIM
import com.spacex.ui.rocketDetails.launches.RocketLaunchUIM

fun RocketDM.mapToUIM(launches: List<RocketLaunchDM>): RocketDetailsUIM {
    return RocketDetailsUIM(
            getImageResId(stringId),
            name,
            description,
            launches.toUIM().sortedWith(compareBy { it.date }).reversed().addYearHeaders()
    )
}

fun List<RocketLaunchDM>.toUIM(): List<RocketLaunchUIM> {
    return List(size) { i -> this[i].toUIM() }
}

fun List<RocketLaunchUIM>.addYearHeaders(): List<RocketLaunchItemUIM> {
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

fun RocketLaunchDM.toUIM(): RocketLaunchUIM {
    return RocketLaunchUIM(missionName, year, date, wasSuccessful, videoLink, wikipediaLink, missionPatchLink)
}