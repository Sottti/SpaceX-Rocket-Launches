package com.spacex.rockets.datasources

import androidx.annotation.WorkerThread
import com.spacex.domain.RocketDM

interface RocketsDS {

    interface Local {

        companion object {
            const val ROCKETS_EXPIRATION_TIME_IN_MILLIS = 30 * 1000
        }

        @WorkerThread
        fun loadAllRockets(callbacks: OnLoadRocketsCallbacks)

        @WorkerThread
        fun insertOrReplaceRockets(rocketsDM: List<RocketDM>)

        @WorkerThread
        fun deleteRockets(rockets: List<RocketDM>)

        interface OnLoadRocketsCallbacks {

            fun onSuccessLoadingRockets(rocketsDM: List<RocketDM>, oldestRocketAgeInMillis: Long)

            fun onRocketsNotFound()
        }
    }

    interface Remote {

        @WorkerThread
        fun loadAllRockets(callbacks: OnLoadRocketsCallbacks)

        fun cancel()

        interface OnLoadRocketsCallbacks {

            fun onSuccessLoadingRockets(rocketsDM: List<RocketDM>)

            fun onErrorLoadingRockets()
        }
    }
}
