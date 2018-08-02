package com.spacex.launches.rockets

import com.spacex.launches.rockets.model.BaseRM

fun List<BaseRM>.getOldestItemAgeInMillis(): Long {
    var oldestItemAge: Long = 0
    forEach { roomModel ->
        if (roomModel.storedTimestamp > oldestItemAge) {
            oldestItemAge = roomModel.storedTimestamp
        }
    }
    return oldestItemAge
}