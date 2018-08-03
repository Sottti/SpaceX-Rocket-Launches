package com.spacex.rockets.repository

import androidx.annotation.WorkerThread
import com.spacex.domain.RocketDM

interface RocketsRepository {

    @WorkerThread
    fun loadAllRockets(callbacks: OnLoadRocketsCallbacks)

    fun cancel()

    interface OnLoadRocketsCallbacks {

        fun onSuccessLoadingRockets(rockets: List<RocketDM>)

        fun onErrorLoadingRockets()
    }
}