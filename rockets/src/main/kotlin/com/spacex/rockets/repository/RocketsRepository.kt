package com.spacex.rockets.repository

import androidx.annotation.WorkerThread
import com.spacex.domain.RocketDM
import com.spacex.domain.RocketLaunchDM

interface RocketsRepository {

    @WorkerThread
    fun loadAllRockets(callbacks: OnLoadRocketsCallbacks)

    fun loadRocket(rocketId: String, callbacks: OnLoadRocketCallbacks)

    fun loadRocketLaunches(rocketId : String, callbacks: OnLoadRocketLaunchesCallbacks)

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

        fun onSuccessLoadingRocketLaunches(rocketLaunches: List<RocketLaunchDM>)

        fun onErrorLoadingRocketLaunches()
    }
}
