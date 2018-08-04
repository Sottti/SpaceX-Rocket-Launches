package com.spacex.ui.rocketDetails

import com.spacex.domain.RocketDM
import com.spacex.ui.getImageResId

fun mapToUIM(rocket: RocketDM): RocketDetailsUIM {
    return RocketDetailsUIM(
            rocket.getImageResId(rocket.stringId),
            rocket.name,
            rocket.description
    )
}