package com.spacex.ui.rocketList

import com.spacex.domain.RocketDM
import com.spacex.ui.getImageResId

fun List<RocketDM>.mapToUIM(): List<RocketUIM> {
    return List(size) { i -> mapToUIM(get(i)) }
}

fun mapToUIM(rocket: RocketDM): RocketUIM {
    return RocketUIM(
            rocket.id,
            rocket.name,
            rocket.country,
            rocket.enginesCount,
            rocket.getImageResId(rocket.stringId)
    )
}