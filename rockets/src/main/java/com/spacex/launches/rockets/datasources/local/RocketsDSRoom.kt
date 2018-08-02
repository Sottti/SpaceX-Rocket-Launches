package com.spacex.launches.rockets.datasources.local

import androidx.annotation.WorkerThread
import com.spacex.launches.domain.RocketDM
import com.spacex.launches.rockets.datasources.RocketsDS
import com.spacex.launches.rockets.getMillisSince
import com.spacex.launches.rockets.getOldestItemAgeInMillis
import com.spacex.launches.rockets.model.RocketsMapper.mapDMToRM
import com.spacex.launches.rockets.model.RocketsMapper.mapRMToDM
import timber.log.Timber

class RocketsDSRoom(private val dao: RocketsDao) : RocketsDS.Local {

    @WorkerThread
    override fun loadAllRockets(callbacks: RocketsDS.Local.OnLoadRocketsCallbacks) {
        val rocketsRM = dao.loadAllRockets()
        if (rocketsRM.isEmpty()) {
            callbacks.onRocketsNotFound()
            Timber.d("No rockets found in Room")
        } else {
            Timber.d("Loaded ${rocketsRM.size} rockets from Room")
            callbacks.onSuccessLoadingRockets(
                    mapRMToDM(rocketsRM),
                    rocketsRM.getOldestItemAgeInMillis().getMillisSince())
        }
    }

    @WorkerThread
    override fun insertOrReplaceRockets(rocketsDM: List<RocketDM>) {
        Timber.d("Inserting ${rocketsDM.size} rockets in Room")
        dao.insertOrReplaceRockets(mapDMToRM(rocketsDM))
    }

    @WorkerThread
    override fun deleteRockets(rockets: List<RocketDM>) {
        if (rockets.isNotEmpty()) {
            Timber.d("Deleting ${rockets.size} rockets from Room")
            dao.deleteRockets(mapDMToRM(rockets))
        }
    }
}