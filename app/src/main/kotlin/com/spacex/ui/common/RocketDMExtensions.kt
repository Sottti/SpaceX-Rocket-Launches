package com.spacex.ui.common

import com.spacex.domain.RocketDM
import com.spacex.ui.R

internal fun RocketDM.getImageResId(stringId: String): Int {
    return when (stringId) {
        "falcon1" -> R.drawable.rocket_falcon_1
        "falcon9" -> R.drawable.rocket_falcon_9
        "falconheavy" -> R.drawable.rocket_falcon_heavy
        "bfr" -> R.drawable.rocket_bfr
        else -> R.drawable.rocket_placeholder
    }
}

