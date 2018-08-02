package com.spacex.launches.rockets.repository

import androidx.annotation.WorkerThread
import com.spacex.launches.domain.RocketDM

interface RocketsRepository {

    @WorkerThread
    fun loadAllRockets(callbacks: OnLoadRocketsCallbacks)

    fun cancel()

    interface OnLoadRocketsCallbacks {

        fun onSuccessLoadingRockets(rockets: List<RocketDM>)

        fun onErrorLoadingRockets()
    }
}
