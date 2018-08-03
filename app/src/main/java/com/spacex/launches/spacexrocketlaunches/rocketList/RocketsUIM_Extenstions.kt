package com.spacex.launches.spacexrocketlaunches.rocketList

import com.spacex.domain.RocketDM
import com.spacex.launches.spacexrocketlaunches.R

fun List<RocketDM>.mapToUIM(): List<RocketUIM> {
    return List(size) { i -> mapToUIM(get(i)) }
}

fun mapToUIM(rocket: RocketDM): RocketUIM {
    return RocketUIM(
            rocket.id,
            rocket.name,
            rocket.country,
            rocket.enginesCount,
            getDrawableResId(rocket.stringId)
    )
}

fun getDrawableResId(stringId: String): Int {
    return when (stringId) {
        "falcon1" -> R.drawable.rocket_falcon_1;
        "falcon9" -> R.drawable.rocket_falcon_9;
        "falconheavy" -> R.drawable.rocket_falcon_heavy;
        "bfr" -> R.drawable.rocket_bfr;
        else -> R.drawable.rocket_placeholder
    }
}
