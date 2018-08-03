package com.spacex.rockets

fun Long.getMillisSince(): Long {
    return System.currentTimeMillis().let { if (this > it) 0 else it - this }
}