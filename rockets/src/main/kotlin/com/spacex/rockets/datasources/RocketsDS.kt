package com.spacex.rockets.datasources

import androidx.annotation.WorkerThread
import com.spacex.domain.LaunchDM
import com.spacex.domain.RocketDM

interface RocketsDS {

    interface Local {

        companion object {
            const val ROCKETS_EXPIRATION_TIME_IN_MILLIS = 30 * 1000
            const val ROCKET_LAUNCHES_EXPIRATION_TIME_IN_MILLIS = 120 * 1000
        }

        @WorkerThread
        fun loadAllRockets(callbacks: OnLoadRocketsCallbacks)

        @WorkerThread
        fun loadRocket(rocketId: String, callbacks: OnLoadRocketCallbacks)

        @WorkerThread
        fun loadRocketLaunches(rocketId: String, callbacks: OnLoadRocketLaunchesCallbacks)

        @WorkerThread
        fun insertOrReplaceRockets(rocket: RocketDM)

        @WorkerThread
        fun insertOrReplaceRockets(rockets: List<RocketDM>)

        @WorkerThread
        fun insertOrReplaceRocketLaunches(launches: List<LaunchDM>)

        @WorkerThread
        fun deleteRockets(rockets: List<RocketDM>)

        @WorkerThread
        fun deleteRocketsLaunches(launches: List<LaunchDM>)

        interface OnLoadRocketsCallbacks {

            fun onSuccessLoadingRockets(rockets: List<RocketDM>, oldestRocketAgeInMillis: Long)

            fun onRocketsNotFound()
        }

        interface OnLoadRocketCallbacks {

            fun onSuccessLoadingRocket(rocket: RocketDM, rocketAgeInMillis: Long)

            fun onRocketNotFound()
        }

        interface OnLoadRocketLaunchesCallbacks {

            fun onSuccessLoadingLaunches(launches: List<LaunchDM>, oldestLaunchAgeInMillis: Long)

            fun onLaunchesNotFound()
        }
    }

    interface Remote {

        @WorkerThread
        fun loadAllRockets(callbacks: OnLoadRocketsCallbacks)

        @WorkerThread
        fun loadRocket(rocketId: String, callbacks: OnLoadRocketCallbacks)

        @WorkerThread
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
}
