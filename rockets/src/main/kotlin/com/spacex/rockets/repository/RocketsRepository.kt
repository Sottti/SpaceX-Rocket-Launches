package com.spacex.rockets.repository

import androidx.annotation.WorkerThread
import com.spacex.domain.LaunchDM
import com.spacex.domain.RocketDM

interface RocketsRepository {

    @WorkerThread
    fun loadAllRockets(callbacks: OnLoadRocketsCallbacks)

    fun loadRocket(rocketId: String, callbacks: OnLoadRocketCallbacks)

    fun loadRocketLaunches(rocketId: String, callbacks: OnLoadRocketLaunchesCallbacks)

    fun cancel()

    interface OnLoadRocketsCallbacks {

        fun onSuccessLoadingRockets(rockets: List<RocketDM>)

        fun onErrorLoadingRockets()
    }

    interface OnLoadRocketCallbacks {

        fun onSuccessLoadingRocket(rocket: RocketDM)

        fun onErrorLoadingRocket()
    }

    interface OnLoadRocketLaunchesCallbacks {

        fun onSuccessLoadingRocketLaunches(launches: List<LaunchDM>)

        fun onErrorLoadingRocketLaunches()
    }
}
