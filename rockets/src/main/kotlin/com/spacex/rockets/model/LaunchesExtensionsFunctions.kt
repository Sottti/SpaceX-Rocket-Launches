package com.spacex.rockets.model

import com.spacex.domain.RocketLaunchDM

internal fun List<RocketLaunchAM>.toDM(): List<RocketLaunchDM> {
    return List(size) { i -> this[i].toDM() }
}

internal fun RocketLaunchAM.toDM(): RocketLaunchDM {
    return RocketLaunchDM(
            missionName,
            date,
            year,
            wasSuccessful,
            links.missionPatch,
            links.videoLink,
            links.wikipedia
    )
}