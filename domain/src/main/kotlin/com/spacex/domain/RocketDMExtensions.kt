package com.spacex.domain

internal fun List<RocketDM>.getActiveRockets(): List<RocketDM> {
    return filter { rocket -> rocket.isActive }
}