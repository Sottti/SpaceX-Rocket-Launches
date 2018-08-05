package com.spacex.ui

import com.spacex.domain.RocketDM

internal fun RocketDM.getImageResId(stringId: String): Int {
    return when (stringId) {
        "falcon1" -> R.drawable.rocket_falcon_1
        "falcon9" -> R.drawable.rocket_falcon_9
        "falconheavy" -> R.drawable.rocket_falcon_heavy
        "bfr" -> R.drawable.rocket_bfr
        else -> R.drawable.rocket_placeholder
    }
}

