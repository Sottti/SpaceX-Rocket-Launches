package com.spacex.domain

fun List<RocketDM>.getActiveRockets(): List<RocketDM> {
    return filter { rocket -> rocket.isActive }
}