package com.spacex.rockets.model

import com.spacex.domain.RocketDM

internal object RocketsMapper {

    fun mapDMToRM(rocketsDM: List<RocketDM>): List<RocketRM> {
        return List(rocketsDM.size) { i -> mapToRM(rocketsDM[i]) }
    }

    private fun mapToRM(rocketDM: RocketDM): RocketRM {
        return RocketRM(
                rocketDM.id,
                rocketDM.stringId,
                rocketDM.name,
                rocketDM.country,
                rocketDM.enginesCount,
                rocketDM.isActive,
                System.currentTimeMillis())
    }

    fun mapRMToDM(rocketsRM: List<RocketRM>): List<RocketDM> {
        return List(rocketsRM.size) { i -> mapToDM(rocketsRM[i]) }
    }

    fun mapAMToDM(rocketsAM: List<RocketAM>): List<RocketDM> {
        return List(rocketsAM.size) { i -> mapToDM(rocketsAM[i]) }
    }

    private fun mapToDM(rocketRM: RocketRM): RocketDM {
        return RocketDM(
                rocketRM.id,
                rocketRM.stringId,
                rocketRM.name,
                rocketRM.country,
                rocketRM.enginesCount,
                rocketRM.isActive)
    }

    private fun mapToDM(rocketAM: RocketAM): RocketDM {
        return RocketDM(
                rocketAM.rocketId,
                rocketAM.id,
                rocketAM.name,
                rocketAM.country,
                rocketAM.engines.number.toString(),
                rocketAM.active)
    }
}
