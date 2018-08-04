package com.spacex.rockets.model

import com.spacex.domain.RocketLaunchDM

fun List<RocketLaunchAM>.toDM(): List<RocketLaunchDM> {
    return List(size) { i -> this[i].toDM() }
}

fun RocketLaunchAM.toDM(): RocketLaunchDM {
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