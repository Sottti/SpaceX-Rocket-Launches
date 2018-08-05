package com.spacex.rockets.model

import com.spacex.rockets.getMillisSince

internal fun List<BaseRM>.getOldestItemAgeInMillis(): Long {
    var oldestItemAge: Long = 0
    forEach { roomModel ->
        if (roomModel.storedTimestamp > oldestItemAge) {
            oldestItemAge = roomModel.storedTimestamp
        }
    }
    return oldestItemAge.getMillisSince()
}
