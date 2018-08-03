package com.spacex.rockets.model

import com.spacex.domain.RocketDM

@JvmName("mapAMToDM")
fun List<RocketAM>.toDM(): List<RocketDM> {
    return List(size) { i -> this[i].toDM() }
}

fun List<RocketDM>.toRM(): List<RocketRM> {
    return List(size) { i -> this[i].toRM() }
}

@JvmName("mapRMToDM")
fun List<RocketRM>.toDM(): List<RocketDM> {
    return List(size) { i -> this[i].toDM() }
}

fun RocketDM.toRM(): RocketRM {
    return RocketRM(id, stringId, name, country, enginesCount, isActive, System.currentTimeMillis())
}

fun RocketAM.toDM(): RocketDM {
    return RocketDM(rocketId, id, name, country, engines.number.toString(), active)
}

fun RocketRM.toDM(): RocketDM {
    return RocketDM(id, stringId, name, country, enginesCount, isActive)
}

fun List<BaseRM>.getOldestItemAgeInMillis(): Long {
    var oldestItemAge: Long = 0
    forEach { roomModel ->
        if (roomModel.storedTimestamp > oldestItemAge) {
            oldestItemAge = roomModel.storedTimestamp
        }
    }
    return System.currentTimeMillis() - oldestItemAge
}
