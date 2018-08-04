package com.spacex.ui.rocketDetails

import com.spacex.domain.RocketDM
import com.spacex.domain.RocketLaunchDM
import com.spacex.ui.getImageResId
import com.spacex.ui.rocketDetails.launches.RocketLaunchUIM

fun RocketDM.mapToUIM(launches: List<RocketLaunchDM>): RocketDetailsUIM {
    return RocketDetailsUIM(
            getImageResId(stringId),
            name,
            description,
            launches.toUIM()
    )
}

fun List<RocketLaunchDM>.toUIM(): List<RocketLaunchUIM> {
    return List(size) { i -> this[i].toUIM() }

}

fun RocketLaunchDM.toUIM(): RocketLaunchUIM {
    return RocketLaunchUIM(missionName, date, wasSuccessful, videoLink, wikipediaLink, missionPatchLink)
}