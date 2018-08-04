package com.spacex.domain

fun List<RocketLaunchDM>.getComplementary(referenceSet: List<RocketLaunchDM>): List<RocketLaunchDM> {
    return filter { objectDM -> !referenceSet.getIds().contains(objectDM.missionName) }
}

private fun List<RocketLaunchDM>.getIds(): List<String> {
    return List(size) { i -> this[i].missionName }
}