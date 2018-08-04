package com.spacex.rockets.repository

import androidx.annotation.WorkerThread
import com.spacex.domain.RocketDM
import com.spacex.domain.RocketLaunchDM
import com.spacex.domain.getComplementary
import com.spacex.rockets.datasources.RocketsDS
import com.spacex.rockets.datasources.RocketsDS.Local.Companion.ROCKETS_EXPIRATION_TIME_IN_MILLIS
import com.spacex.rockets.datasources.RocketsDS.Local.Companion.ROCKET_LAUNCHES_EXPIRATION_TIME_IN_MILLIS

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
                deleteComplementaryRocketsFromLocalDS(rockets, rocketsFoundLocally)
                callbacks.onSuccessLoadingRockets(rockets)
            }

            override fun onErrorLoadingRockets() {
                if (propagateError) {
                    callbacks.onErrorLoadingRockets()
                }
            }
        })
    }

    override fun loadRocket(rocketId: String, callbacks: RocketsRepository.OnLoadRocketCallbacks) {
        localDS.loadRocket(rocketId, object : RocketsDS.Local.OnLoadRocketCallbacks {
            override fun onSuccessLoadingRocket(
                    rocket: RocketDM,
                    rocketAgeInMillis: Long) {
                callbacks.onSuccessLoadingRocket(rocket)
                if (rocketAgeInMillis > ROCKETS_EXPIRATION_TIME_IN_MILLIS) {
                    loadRocketFromRemoteDS(rocketId, callbacks, false)
                }
            }

            override fun onRocketNotFound() {
                loadRocketFromRemoteDS(rocketId, callbacks)
            }
        })
    }

    @WorkerThread
    private fun loadRocketFromRemoteDS(
            rocketId: String,
            callbacks: RocketsRepository.OnLoadRocketCallbacks,
            propagateError: Boolean = true) {
        remoteDS.loadRocket(rocketId, object : RocketsDS.Remote.OnLoadRocketCallbacks {
            override fun onSuccessLoadingRocket(rocket: RocketDM) {
                localDS.insertOrReplaceRockets(rocket)
                callbacks.onSuccessLoadingRocket(rocket)
            }

            override fun onErrorLoadingRocket() {
                if (propagateError) {
                    callbacks.onErrorLoadingRocket()
                }
            }
        })
    }

    override fun loadRocketLaunches(rocketId: String, callbacks: RocketsRepository.OnLoadRocketLaunchesCallbacks) {
        localDS.loadRocketLaunches(rocketId, object : RocketsDS.Local.OnLoadRocketLaunchesCallbacks {
            override fun onSuccessLoadingRocketLaunches(rocketLaunches: List<RocketLaunchDM>, oldestLaunchAgeInMillis: Long) {
                callbacks.onSuccessLoadingRocketLaunches(rocketLaunches)
                if (oldestLaunchAgeInMillis > ROCKET_LAUNCHES_EXPIRATION_TIME_IN_MILLIS) {
                    loadRocketLaunchesFromRemoteDS(rocketId, callbacks, false, rocketLaunches)
                }
            }

            override fun onRocketLaunchesNotFound() {
                loadRocketLaunchesFromRemoteDS(rocketId, callbacks)
            }

        })
    }

    @WorkerThread
    private fun loadRocketLaunchesFromRemoteDS(
            rocketId: String,
            callbacks: RocketsRepository.OnLoadRocketLaunchesCallbacks,
            propagateError: Boolean = true,
            rocketsFoundLocally: List<RocketLaunchDM> = emptyList()) {
        remoteDS.loadRocketLaunches(rocketId, object : RocketsDS.Remote.OnLoadRocketLaunchesCallbacks {
            override fun onSuccessLoadingRocketLaunches(rocketLaunches: List<RocketLaunchDM>) {
                localDS.insertOrReplaceRocketLaunches(rocketLaunches)
                deleteComplementaryRocketLaunchesFromLocalDS(rocketLaunches, rocketsFoundLocally)
                callbacks.onSuccessLoadingRocketLaunches(rocketLaunches)
            }

            override fun onErrorLoadingRocketLaunches() {
                if (propagateError) {
                    callbacks.onErrorLoadingRocketLaunches()
                }
            }
        })
    }

    @WorkerThread
    private fun deleteComplementaryRocketsFromLocalDS(
            rocketsFromRemote: List<RocketDM>,
            rocketsFromLocal: List<RocketDM>) {
        if (rocketsFromLocal.isNotEmpty()) {
            localDS.deleteRockets(rocketsFromRemote.getComplementary(rocketsFromLocal))
        }
    }

    @WorkerThread
    private fun deleteComplementaryRocketLaunchesFromLocalDS(
            rocketsFromRemote: List<RocketLaunchDM>,
            rocketsFromLocal: List<RocketLaunchDM>) {
        if (rocketsFromLocal.isNotEmpty()) {
            localDS.deleteRocketsLaunches(rocketsFromRemote.getComplementary(rocketsFromLocal))
        }
    }

    override fun cancel() {
        remoteDS.cancel()
    }
}