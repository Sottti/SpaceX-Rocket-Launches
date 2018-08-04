package com.spacex.rockets.model

import com.spacex.domain.RocketDM
import com.spacex.rockets.getMillisSince

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
    return RocketRM(id, stringId, name, country, enginesCount, isActive, description, System.currentTimeMillis())
}

fun RocketAM.toDM(): RocketDM {
    return RocketDM(rocketId, id, name, country, engines.number.toString(), active, description)
}

fun RocketRM.toDM(): RocketDM {
    return RocketDM(id, stringId, name, country, enginesCount, isActive, description)
}

fun RocketRM.getAgeInMillis(): Long {
    return storedTimestamp.getMillisSince()
}