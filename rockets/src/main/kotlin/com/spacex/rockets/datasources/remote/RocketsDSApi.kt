package com.spacex.rockets.datasources.remote

import androidx.annotation.WorkerThread
import com.spacex.rockets.datasources.RocketsDS
import com.spacex.rockets.model.LaunchAM
import com.spacex.rockets.model.RocketAM
import com.spacex.rockets.model.toDM
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

internal class RocketsDSApi(private val service: RocketsApiService) : RocketsDS.Remote {

    private var loadRocket: Call<RocketAM>? = null
    private var mLoadLaunches: Call<List<LaunchAM>>? = null
    private var loadAllRockets: Call<List<RocketAM>>? = null

    @WorkerThread
    override fun loadAllRockets(callbacks: RocketsDS.Remote.OnLoadRocketsCallbacks) {
        loadAllRockets = service.loadAllRockets()
        loadAllRockets!!.enqueue(object : Callback<List<RocketAM>> {
            override fun onResponse(call: Call<List<RocketAM>>, response: Response<List<RocketAM>>) {
                val rockets = response.body()
                if (response.isSuccessful && rockets != null) {
                    Timber.d("Received ${rockets.size} rockets from the API")
                    callbacks.onSuccessLoadingRockets(rockets.toDM())
                } else {
                    Timber.d("Error loading rockets from the API")
                    callbacks.onErrorLoadingRockets()
                }
            }

            override fun onFailure(call: Call<List<RocketAM>>, t: Throwable) {
                if (!call.isCanceled) {
                    callbacks.onErrorLoadingRockets()
                    Timber.d("Loading rockets from the API cancelled")
                }
            }
        })
    }

    override fun loadRocket(rocketId: String, callbacks: RocketsDS.Remote.OnLoadRocketCallbacks) {
        loadRocket = service.loadRocket(rocketId)
        loadRocket!!.enqueue(object : Callback<RocketAM> {
            override fun onResponse(call: Call<RocketAM>, response: Response<RocketAM>) {
                val rockets = response.body()
                if (response.isSuccessful && rockets != null) {
                    Timber.d("Received ${rockets.name} rocket from the API")
                    callbacks.onSuccessLoadingRocket(rockets.toDM())
                } else {
                    Timber.d("Error loading rockets from the API")
                    callbacks.onErrorLoadingRocket()
                }
            }

            override fun onFailure(call: Call<RocketAM>, t: Throwable) {
                if (!call.isCanceled) {
                    callbacks.onErrorLoadingRocket()
                    Timber.d("Loading rocket from the API cancelled")
                }
            }
        })
    }

    override fun loadRocketLaunches(rocketId: String, callbacks: RocketsDS.Remote.OnLoadRocketLaunchesCallbacks) {
        mLoadLaunches = service.loadRocketLaunches(rocketId)
        mLoadLaunches!!.enqueue(object : Callback<List<LaunchAM>> {
            override fun onResponse(call: Call<List<LaunchAM>>, response: Response<List<LaunchAM>>) {
                val launches = response.body()
                if (response.isSuccessful && launches != null) {
                    Timber.d("Received ${launches.size} rocket launches from the API")
                    callbacks.onSuccessLoadingRocketLaunches(launches.toDM())
                } else {
                    Timber.d("Error loading rocket launches from the API")
                    callbacks.onErrorLoadingRocketLaunches()
                }
            }

            override fun onFailure(call: Call<List<LaunchAM>>, t: Throwable) {
                if (!call.isCanceled) {
                    callbacks.onErrorLoadingRocketLaunches()
                    Timber.d("Loading rockets launches from the API cancelled")
                }
            }
        })
    }

    override fun cancel() {
        loadRocket?.cancel()
        loadAllRockets?.cancel()
        mLoadLaunches?.cancel()
    }
}
