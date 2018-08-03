package com.spacex.rockets.datasources.local

import androidx.annotation.WorkerThread
import com.spacex.domain.RocketDM
import com.spacex.rockets.datasources.RocketsDS
import com.spacex.rockets.getMillisSince
import com.spacex.rockets.model.getOldestItemAgeInMillis
import com.spacex.rockets.model.toDM
import com.spacex.rockets.model.toRM
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
                    rocketsRM.toDM(),
                    rocketsRM.getOldestItemAgeInMillis().getMillisSince())
        }
    }

    @WorkerThread
    override fun insertOrReplaceRockets(rockets: List<RocketDM>) {
        Timber.d("Inserting ${rockets.size} rockets in Room")
        dao.insertOrReplaceRockets(rockets.toRM())
    }

    @WorkerThread
    override fun deleteRockets(rockets: List<RocketDM>) {
        if (rockets.isNotEmpty()) {
            Timber.d("Deleting ${rockets.size} rockets from Room")
            dao.deleteRockets(rockets.toRM())
        }
    }
}