package com.spacex.rockets.datasources.remote

import androidx.annotation.WorkerThread
import com.spacex.rockets.datasources.RocketsDS
import com.spacex.rockets.model.RocketAM
import com.spacex.rockets.model.toDM
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

internal class RocketsDSApi(service: RocketsApiService) : RocketsDS.Remote {

    private var loadAllRocketsCall: Call<List<RocketAM>> = service.getAllRockets()

    @WorkerThread
    override fun loadAllRockets(callbacks: RocketsDS.Remote.OnLoadRocketsCallbacks) {

        if (loadAllRocketsCall.isExecuted) {
            loadAllRocketsCall = loadAllRocketsCall.clone()
        }
        dispatchRockets(loadAllRocketsCall, callbacks)
    }

    @WorkerThread
    private fun dispatchRockets(
            call: Call<List<RocketAM>>,
            onLoadRocketsCallbacks: RocketsDS.Remote.OnLoadRocketsCallbacks) {
        call.enqueue(object : Callback<List<RocketAM>> {
            override fun onResponse(
                    call: Call<List<RocketAM>>,
                    response: Response<List<RocketAM>>) {
                val rockets = response.body()
                if (response.isSuccessful && rockets != null) {
                    Timber.d("Received ${rockets.size} rockets from the API")
                    onLoadRocketsCallbacks.onSuccessLoadingRockets(rockets.toDM())
                } else {
                    Timber.d("Error loading rockets from the API")
                    onLoadRocketsCallbacks.onErrorLoadingRockets()
                }
            }

            override fun onFailure(call: Call<List<RocketAM>>, t: Throwable) {
                if (!call.isCanceled) {
                    onLoadRocketsCallbacks.onErrorLoadingRockets()
                    Timber.d("Loading rockets from the API cancelled")
                }
            }
        })
    }

    override fun cancel() {
        loadAllRocketsCall.cancel()
    }
}
