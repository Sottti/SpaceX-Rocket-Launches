package com.spacex.rockets.repository

import androidx.annotation.WorkerThread
import com.spacex.domain.RocketDM
import com.spacex.domain.getComplementary
import com.spacex.rockets.datasources.RocketsDS
import com.spacex.rockets.datasources.RocketsDS.Local.Companion.ROCKETS_EXPIRATION_TIME_IN_MILLIS

class RocketsRepositoryImpl(
        private val localDS: RocketsDS.Local,
        private val remoteDS: RocketsDS.Remote) : RocketsRepository {

    @WorkerThread
    override fun loadAllRockets(callbacks: RocketsRepository.OnLoadRocketsCallbacks) {
        localDS.loadAllRockets(object : RocketsDS.Local.OnLoadRocketsCallbacks {
            override fun onSuccessLoadingRockets(
                    rockets: List<RocketDM>,
                    oldestRocketAgeInMillis: Long) {
                callbacks.onSuccessLoadingRockets(rockets)
                if (oldestRocketAgeInMillis > ROCKETS_EXPIRATION_TIME_IN_MILLIS) {
                    loadAllRocketsFromRemoteDS(callbacks, false, rockets)
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
            override fun onSuccessLoadingRockets(rockets: List<RocketDM>) {
                localDS.insertOrReplaceRockets(rockets)
                deleteComplementaryFromLocalDS(rockets, rocketsFoundLocally)
                callbacks.onSuccessLoadingRockets(rockets)
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
            rocketsFromRemote: List<RocketDM>,
            rocketsFromLocal: List<RocketDM>) {
        localDS.deleteRockets(rocketsFromRemote.getComplementary(rocketsFromLocal))
    }

    override fun cancel() {
        remoteDS.cancel()
    }
}