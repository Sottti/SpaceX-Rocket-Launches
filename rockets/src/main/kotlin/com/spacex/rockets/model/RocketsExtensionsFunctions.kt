package com.spacex.rockets.model

import com.spacex.domain.RocketDM
import com.spacex.rockets.getMillisSince

@JvmName("mapAMToDM")
internal fun List<RocketAM>.toDM(): List<RocketDM> {
    return List(size) { i -> this[i].toDM() }
}

internal fun List<RocketDM>.toRM(): List<RocketRM> {
    return List(size) { i -> this[i].toRM() }
}

@JvmName("mapRMToDM")
internal fun List<RocketRM>.toDM(): List<RocketDM> {
    return List(size) { i -> this[i].toDM() }
}

internal fun RocketDM.toRM(): RocketRM {
    return RocketRM(id, stringId, name, country, enginesCount, isActive, description, System.currentTimeMillis())
}

internal fun RocketAM.toDM(): RocketDM {
    return RocketDM(rocketId, id, name, country, engines.number.toString(), active, description)
}

internal fun RocketRM.toDM(): RocketDM {
    return RocketDM(id, stringId, name, country, enginesCount, isActive, description)
}

internal fun RocketRM.getAgeInMillis(): Long {
    return storedTimestamp.getMillisSince()
}