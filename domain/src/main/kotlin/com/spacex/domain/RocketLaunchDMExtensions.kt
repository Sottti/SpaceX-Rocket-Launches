package com.spacex.domain

fun List<LaunchDM>.getComplementary(referenceSet: List<LaunchDM>): List<LaunchDM> {
    return filter { objectDM -> !referenceSet.getIds().contains(objectDM.missionName) }
}

private fun List<LaunchDM>.getIds(): List<String> {
    return List(size) { i -> this[i].missionName }
}