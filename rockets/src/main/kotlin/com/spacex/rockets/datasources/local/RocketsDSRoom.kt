package com.spacex.rockets.datasources.local

import androidx.annotation.WorkerThread
import com.spacex.domain.RocketDM
import com.spacex.domain.RocketLaunchDM
import com.spacex.rockets.datasources.RocketsDS
import com.spacex.rockets.model.getAgeInMillis
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
                    rocketsRM.getOldestItemAgeInMillis())
        }
    }

    override fun loadRocket(rocketId: String, callbacks: RocketsDS.Local.OnLoadRocketCallbacks) {
        val rocketRM = dao.loadRocket(rocketId)
        if (rocketRM == null) {
            Timber.d("Rocket not found in Room")
            callbacks.onRocketNotFound()
        } else {
            Timber.d("Loaded ${rocketRM.name} rocket from Room")
            callbacks.onSuccessLoadingRocket(rocketRM.toDM(), rocketRM.getAgeInMillis())
        }

    }

    override fun insertOrReplaceRockets(rocket: RocketDM) {
        insertOrReplaceRockets(listOf(rocket))
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

    override fun loadRocketLaunches(rocketId: String, callbacks: RocketsDS.Local.OnLoadRocketLaunchesCallbacks) {
        callbacks.onRocketLaunchesNotFound()
    }

    override fun insertOrReplaceRocketLaunches(rocketLaunches: List<RocketLaunchDM>) {

    }

    override fun deleteRocketsLaunches(rockets: List<RocketLaunchDM>) {

    }
}