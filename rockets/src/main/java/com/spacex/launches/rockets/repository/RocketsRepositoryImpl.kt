package com.spacex.launches.rockets.repository

import androidx.annotation.WorkerThread
import com.spacex.launches.domain.DomainModelUtils.getComplementary
import com.spacex.launches.domain.RocketDM
import com.spacex.launches.rockets.datasources.RocketsDS
import com.spacex.launches.rockets.datasources.RocketsDS.Local.Companion.ROCKETS_EXPIRATION_TIME_IN_MILLIS

internal class RocketsRepositoryImpl(
        val localDS: RocketsDS.Local,
        private val remoteDS: RocketsDS.Remote) : RocketsRepository {

    @WorkerThread
    override fun loadAllRockets(callbacks: RocketsRepository.OnLoadRocketsCallbacks) {
        localDS.loadAllRockets(object : RocketsDS.Local.OnLoadRocketsCallbacks {
            override fun onSuccessLoadingRockets(
                    rocketsDM: List<RocketDM>,
                    oldestRocketAgeInMillis: Long) {
                callbacks.onSuccessLoadingRockets(rocketsDM)
                if (oldestRocketAgeInMillis > ROCKETS_EXPIRATION_TIME_IN_MILLIS) {
                    loadAllRocketsFromRemoteDS(callbacks, false, rocketsDM)
                }
            }

            override fun onRocketsNotFound() {
                loadAllRocketsFromRemoteDS(callbacks)
            }
        })
    }

    @WorkerThread
    private fun loadAllRocketsFromRemoteDS(
            callbacks: RocketsRepository.OnLoadRocketsCallbacks,
            propagateError: Boolean = true,
            rocketsFoundLocally: List<RocketDM> = emptyList()) {
        remoteDS.loadAllRockets(object : RocketsDS.Remote.OnLoadRocketsCallbacks {
            override fun onSuccessLoadingRockets(rocketsDM: List<RocketDM>) {
                localDS.insertOrReplaceRockets(rocketsDM)
                deleteComplementaryFromLocalDS(rocketsDM, rocketsFoundLocally)
                callbacks.onSuccessLoadingRockets(rocketsDM)
            }

            override fun onErrorLoadingRockets() {
                if (propagateError) {
                    callbacks.onErrorLoadingRockets()
                }
            }
        })
    }

    @WorkerThread
    private fun deleteComplementaryFromLocalDS(
            rocketsFoundRemotely: List<RocketDM>,
            rocketsFoundLocally: List<RocketDM>) {
        if (rocketsFoundLocally.isNotEmpty()) {
            localDS.deleteRockets(getComplementary(rocketsFoundRemotely, rocketsFoundLocally))
        }
    }

    override fun cancel() {
        remoteDS.cancel()
    }
}