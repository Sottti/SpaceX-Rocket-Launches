package com.spacex.ui.common

internal fun isAtLeastLollipop(): Boolean {
    return android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP
}