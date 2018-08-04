package com.spacex.rockets.datasources.remote

import android.content.Context
import com.spacex.rockets.model.RocketAM
import com.spacex.rockets.model.RocketLaunchAM
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

class RocketsApiService internal constructor(context: Context) {

    private val rocketsService: RocketsService =
            getRocketsRetrofit(context).create(RocketsService::class.java)

    fun loadAllRockets(): Call<List<RocketAM>> {
        return rocketsService.loadAllRockets
    }

    fun loadRocket(rocketId: String): Call<RocketAM> {
        return rocketsService.loadRocket(rocketId)
    }

    fun loadRocketLaunches(rocketId: String): Call<List<RocketLaunchAM>> {
        return rocketsService.loadRocketLaunches(rocketId)
    }

    private interface RocketsService {
        @get:GET("/v2/rockets")
        val loadAllRockets: Call<List<RocketAM>>

        @GET("/v2/rocket/{rocketId}")
        fun loadRocket(@Path("rocketId") rocketId: String): Call<RocketAM>

        @GET("/v2/launches")
        fun loadRocketLaunches(@Query("rocket_id") rocketId: String): Call<List<RocketLaunchAM>>
    }
}