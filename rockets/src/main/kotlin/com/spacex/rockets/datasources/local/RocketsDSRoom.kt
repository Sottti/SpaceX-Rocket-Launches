package com.spacex.rockets.datasources.local

import androidx.annotation.WorkerThread
import com.spacex.domain.LaunchDM
import com.spacex.domain.RocketDM
import com.spacex.rockets.datasources.RocketsDS
import com.spacex.rockets.model.getAgeInMillis
import com.spacex.rockets.model.getOldestItemAgeInMillis
import com.spacex.rockets.model.toDM
import com.spacex.rockets.model.toRM
import timber.log.Timber

internal class RocketsDSRoom(
        private val rocketsDao: RocketsDao,
        private val launchesDao: LaunchesDao) : RocketsDS.Local {

    @WorkerThread
    override fun loadAllRockets(callbacks: RocketsDS.Local.OnLoadRocketsCallbacks) {
        val rocketsRM = rocketsDao.loadAllRockets()
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
        val rocketRM = rocketsDao.loadRocket(rocketId)
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
        rocketsDao.insertOrReplaceRockets(rockets.toRM())
    }

    @WorkerThread
    override fun deleteRockets(rockets: List<RocketDM>) {
        if (rockets.isNotEmpty()) {
            Timber.d("Deleting ${rockets.size} rockets from Room")
            rocketsDao.deleteRockets(rockets.toRM())
        }
    }

    override fun loadRocketLaunches(
            rocketId: String,
            callbacks: RocketsDS.Local.OnLoadRocketLaunchesCallbacks) {
        val launchesRM = launchesDao.loadLaunchesForRocket(rocketId)
        if (launchesRM.isEmpty()) {
            callbacks.onLaunchesNotFound()
            Timber.d("No launches for rocket found in Room")
        } else {
            Timber.d("Loaded ${launchesRM.size} launches from Room")
            callbacks.onSuccessLoadingLaunches(
                    launchesRM.toDM(),
                    launchesRM.getOldestItemAgeInMillis())
        }
    }

    override fun insertOrReplaceRocketLaunches(launches: List<LaunchDM>) {
        Timber.d("Inserting ${launches.size} launches in Room")
        launchesDao.insertOrReplaceLaunches(launches.toRM())
    }

    override fun deleteRocketsLaunches(launches: List<LaunchDM>) {
        if (launches.isNotEmpty()) {
            Timber.d("Deleting ${launches.size} launches from Room")
            launchesDao.deleteLaunches(launches.toRM())
        }
    }
}