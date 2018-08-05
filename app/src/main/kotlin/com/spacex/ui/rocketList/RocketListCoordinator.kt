package com.spacex.ui.rocketList

import android.os.Handler
import com.spacex.domain.RocketDM
import com.spacex.domain.getActiveRockets
import com.spacex.rockets.repository.RocketsRepository
import com.spacex.rockets.repository.RocketsRepository.OnLoadRocketsCallbacks
import java.util.concurrent.Executor

internal class RocketListCoordinator(
        private val rocketsRepository: RocketsRepository,
        private val mainThreadHandler: Handler,
        private val backgroundExecutor: Executor) : RocketListContract.Coordinator {

    override fun loadAllRockets(callbacks: RocketListContract.Coordinator.OnLoadRocketListCallbacks) {
        backgroundExecutor.execute {
            rocketsRepository.loadAllRockets(object : OnLoadRocketsCallbacks {
                override fun onSuccessLoadingRockets(rockets: List<RocketDM>) {
                    val rocketsUIM = rockets.mapToUIM()
                    mainThreadHandler.post { callbacks.onSuccessLoadingRocketList(rocketsUIM) }
                }

                override fun onErrorLoadingRockets() {
                    mainThreadHandler.post { callbacks.onErrorLoadingRocketList() }
                }
            })
        }
    }

    override fun loadActiveRockets(callbacks: RocketListContract.Coordinator.OnLoadRocketListCallbacks) {
        backgroundExecutor.execute {
            rocketsRepository.loadAllRockets(object : OnLoadRocketsCallbacks {
                override fun onSuccessLoadingRockets(rockets: List<RocketDM>) {
                    val rocketsUIM = rockets.getActiveRockets().mapToUIM()
                    mainThreadHandler.post { callbacks.onSuccessLoadingRocketList(rocketsUIM) }
                }

                override fun onErrorLoadingRockets() {
                    mainThreadHandler.post { callbacks.onErrorLoadingRocketList() }
                }
            })
        }
    }

    override fun cancel() {
        rocketsRepository.cancel()
    }
}
