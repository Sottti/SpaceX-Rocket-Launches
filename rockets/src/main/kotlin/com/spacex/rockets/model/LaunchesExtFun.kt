package com.spacex.rockets.model

import com.spacex.domain.LaunchDM

@JvmName("mapAMToDM")
internal fun List<LaunchAM>.toDM(): List<LaunchDM> {
    return List(size) { i -> this[i].toDM() }
}

@JvmName("mapADMoRM")
internal fun List<LaunchDM>.toRM(): List<LaunchRM> {
    return List(size) { i -> this[i].toRM() }
}

@JvmName("mapRMToDM")
internal fun List<LaunchRM>.toDM(): List<LaunchDM> {
    return List(size) { i -> this[i].toDM() }
}

internal fun LaunchAM.toDM(): LaunchDM {
    return LaunchDM(
            missionName,
            rocket.rocketId,
            date,
            year,
            wasSuccessful,
            links.missionPatch,
            links.videoLink,
            links.wikipedia
    )
}

internal fun LaunchDM.toRM(): LaunchRM {
    return LaunchRM(
            missionName,
            rocketId,
            date,
            year,
            wasSuccessful,
            missionPatchLink,
            videoLink,
            wikipediaLink,
            System.currentTimeMillis()
    )
}

internal fun LaunchRM.toDM(): LaunchDM {
    return LaunchDM(
            id,
            rocketId,
            date,
            year,
            wasSuccessful,
            missionPatchLink,
            videoLink,
            wikipediaLink
    )
}