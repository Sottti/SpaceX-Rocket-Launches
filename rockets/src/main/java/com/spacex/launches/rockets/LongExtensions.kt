package com.spacex.launches.rockets

fun Long.getMillisSince(): Long {
    val now = System.currentTimeMillis()
    return if (this > now) {
        0
    } else now - this
}