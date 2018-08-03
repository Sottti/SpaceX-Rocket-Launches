package com.spacex.rockets

import com.spacex.rockets.model.BaseRM

fun List<BaseRM>.getOldestItemAgeInMillis(): Long {
    var oldestItemAge: Long = 0
    forEach { roomModel ->
        if (roomModel.storedTimestamp > oldestItemAge) {
            oldestItemAge = roomModel.storedTimestamp
        }
    }
    return System.currentTimeMillis() - oldestItemAge
}