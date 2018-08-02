package com.spacex.launches.spacexrocketlaunches

object AndroidOS {

    val isAtLeastLollipop: Boolean
        get() = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP
}
