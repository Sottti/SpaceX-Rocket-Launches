package com.spacex.ui.rocketDetails

import android.os.Handler
import androidx.annotation.WorkerThread
import com.spacex.domain.LaunchDM
import com.spacex.domain.RocketDM
import com.spacex.rockets.repository.RocketsRepository
import com.spacex.rockets.repository.RocketsRepository.OnLoadRocketCallbacks
import com.spacex.rockets.repository.RocketsRepository.OnLoadRocketLaunchesCallbacks
import java.util.concurrent.Executor

class RocketDetailsCoordinator internal constructor(
        private val rocketsRepository: RocketsRepository,
        private val mainThreadHandler: Handler,
        private val backgroundExecutor: Executor) : RocketDetailsContract.Coordinator {

    override fun getRocketDetailsUIM(
            rocketId: String,
            callbacks: RocketDetailsContract.Coordinator.OnLoadRocketDetailsCallbacks) {
        backgroundExecutor.execute {
            rocketsRepository.loadRocket(rocketId, object : OnLoadRocketCallbacks {
                override fun onSuccessLoadingRocket(rocket: RocketDM) {
                    onRocketReceived(rocket, callbacks)
                }

                override fun onErrorLoadingRocket() {
                    mainThreadHandler.post { callbacks.onErrorLoadingRocketDetails() }
                }
            })
        }
    }

    @WorkerThread
    private fun onRocketReceived(
            rocket: RocketDM,
            callbacks: RocketDetailsContract.Coordinator.OnLoadRocketDetailsCallbacks) {
        rocketsRepository.loadRocketLaunches(rocket.stringId, object : OnLoadRocketLaunchesCallbacks {
            override fun onSuccessLoadingRocketLaunches(launches: List<LaunchDM>) {
                val rocketDetailsUIM = rocket.mapToUIM(launches)
                mainThreadHandler.post { callbacks.onSuccessLoadingRocketDetails(rocketDetailsUIM) }
            }

            override fun onErrorLoadingRocketLaunches() {
                mainThreadHandler.post { callbacks.onErrorLoadingRocketDetails() }
            }
        })
    }

    override fun cancel() {
        rocketsRepository.cancel()
    }
}
