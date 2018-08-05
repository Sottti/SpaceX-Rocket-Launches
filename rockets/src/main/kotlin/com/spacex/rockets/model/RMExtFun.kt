package com.spacex.rockets.model

import com.spacex.rockets.getMillisSince

@JvmName("getOldestItemAgeForBaseRM")
internal fun List<BaseRM>.getOldestItemAgeInMillis(): Long {
    var oldestItemAge: Long = 0
    forEach { roomModel ->
        if (roomModel.storedTimestamp > oldestItemAge) {
            oldestItemAge = roomModel.storedTimestamp
        }
    }
    return oldestItemAge.getMillisSince()
}

@JvmName("getOldestItemAgeForBaseRMStringId")
internal fun List<BaseRMStringId>.getOldestItemAgeInMillis(): Long {
    var oldestItemAge: Long = 0
    forEach { roomModel ->
        if (roomModel.storedTimestamp > oldestItemAge) {
            oldestItemAge = roomModel.storedTimestamp
        }
    }
    return oldestItemAge.getMillisSince()
}
